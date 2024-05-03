package persistence;

import org.json.JSONObject;

// an object which its information should be saved when user choose to save the game
public interface Writable {
    // EFFECTS: return this as an JSON object
    JSONObject toJson();
}
