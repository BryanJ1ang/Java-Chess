package persistence;

import model.Event;
import model.EventLog;
import model.Game;
import org.json.JSONObject;


import java.io.*;


// Represents a writer that writes JSON representation of game to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private final String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of game to file
    public void write(Game g) {
        JSONObject json = g.toJson();
        saveToFile(json.toString(TAB));
        logSavedFile(destination);
    }


    // EFFECTS: Extracts only filename from file
    private String fileNameOnly(String file) {
        String message;
        int length = file.length();
        message = file.substring(7,length - 5);
        return message;
    }


    // MODIFIES: EventLog
    // EFFECT: logs a file has been saved
    private void logSavedFile(String filename) {
        String message = "Game saved as: " + fileNameOnly(filename);
        Event event = new Event(message);
        EventLog.getInstance().logEvent(event);
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }

}
