package persistence;

import org.json.JSONObject;

// Reference: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// a object which its information should be saved when user choose to save the game
public interface Writable {
    // EFFECTS: return this as an JSON object
    JSONObject toJson();
}
