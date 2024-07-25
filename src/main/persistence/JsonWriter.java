package persistence;

import model.*;

import org.json.JSONArray;

import java.io.*;
import java.util.List;

// Represents a writer that writes JSON representation of recipe to file
public class JsonWriter {
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file
    // cannot be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of recipe to file
    public void write(List<Recipe> recipes) {
        JSONArray jsonArray = new JSONArray();
        for (Recipe recipe : recipes) {
            jsonArray.put(recipe.toJson());
        }
        saveToFile(jsonArray.toString(4));
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