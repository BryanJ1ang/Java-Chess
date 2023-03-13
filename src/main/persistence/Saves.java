package persistence;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

// Represents saves class that keeps track of saved games in data
public class Saves {
    private JSONObject json;
    private JSONArray jarray;
    private static final int TAB = 4;
    private PrintWriter writer;
    private final String destination = "./data/saves.json";


    // EFFECTS: Constructor for saves
    public Saves() {
        try {
            json = read();
            jarray = (JSONArray) json.get("list");
        } catch (Exception exception) {
            json = new JSONObject();
            jarray = new JSONArray();
        }
        json.put("list", jarray);
    }

    // EFFECTS: produce true if a save exists of the given name
    public Boolean doesSaveExist(String name) {
        return json.opt(name) != null;
    }

    // EFFECTS: returns a list of saves
    public List listOfSaves() {
        return (List<String>) (List) jarray.toList();
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public JSONObject read() throws IOException {
        String jsonData = readFile(destination);
        JSONObject jsonObject = new JSONObject(jsonData);
        return jsonObject;
    }

    // EFFECTS: reads source file as string and returns it
    // CITATION: JsonSerializationDemo
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }


    // MODIFIES: this
    // EFFECTS: saves destination name
    public void addFile(String name) throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
        json.put(name, name);
        if (!jarray.toList().contains(name)) {
            jarray.put(name);
        }
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: removes file from saves
    public void removeFile(String filename) throws IOException {
        writer = new PrintWriter(new File(destination));
        json.remove(filename);
        saveToFile(json.toString(TAB));
        File file = new File("./data/" + filename + ".json");
        file.delete();
    }

    // MODIFIES: this
    // EFFECTS: clears the saves json
    public void clearSaves() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
        json = new JSONObject();
        jarray = new JSONArray();
        json.put("list", jarray);
        saveToFile(json.toString(TAB));
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
