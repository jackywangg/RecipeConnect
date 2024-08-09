package persistence;

import org.json.JSONObject;

// Sets up a contract between Object and Json
public interface Writable {
    JSONObject toJson();
}
