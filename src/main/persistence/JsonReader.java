package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads game from JSON data stored in file
// CITATION: JsonSerializationDemo
public class JsonReader {
    private final String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads game from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Game read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGame(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: Extracts only filename from file
    private String fileNameOnly(String file) {
        String message;
        int length = file.length();
        message = file.substring(7,length - 5);
        return message;
    }


    // MODIFIES: this
    // EFFECTS: logs a game has been read
    private void logReadGame(String filename) {
        String message = "Loaded from saved file: " + fileNameOnly(filename);
        Event event = new Event(message);
        EventLog.getInstance().logEvent(event);
    }


    // EFFECTS: parses Game from JSON object and returns it
    private Game parseGame(JSONObject jsonObject) {
        Boolean b = (Boolean) jsonObject.get("player1turn");
        JSONArray pieces = (JSONArray) jsonObject.get("pieces");
        JSONArray colours = (JSONArray) jsonObject.get("colours");
        Piecelibrary library = new Piecelibrary();

        Game g;
        Board bd = new Board();
        g = new Game("empty", bd, b);
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (!pieces.get(0).equals("null")) {
                    String type = (String) pieces.get(0);

                    String colour = (String) colours.get(0);

                    Piece p = library.retrievePieceFromLibrary(type, colour);
                    if (p.getWhite()) {
                        g.getPlayer1().addPiece(p);
                    } else {
                        g.getPlayer2().addPiece(p);
                    }

                    bd.addPiece(p, x, y);

                }
                pieces.remove(0);
                colours.remove(0);
            }
        }

        logReadGame(source);
        return g;
    }

}
