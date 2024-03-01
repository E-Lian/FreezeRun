package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.*;
import org.json.*;

// Reference: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents a reader that reads game data from JSON file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads game from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Game read() throws IOException {
        String jsonData = readFile(source);
        JSONObject gameData = new JSONObject(jsonData);
        return parseGame(gameData);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses game from JSON object and returns it
    private Game parseGame(JSONObject gameData) {
        // TODO: check and test reader
        Game game = new Game(gameData.getInt("maxX"), gameData.getInt("maxY"));
        game.setFrozen(gameData.getBoolean("frozen"));
        addFireballs(game, gameData);
        addEnemies(game, gameData);

        return game;
    }

    // MODIFIES: g
    // EFFECTS: parses enemies from JSON object and adds them to game
    private void addEnemies(Game g, JSONObject gameData) {
        JSONArray enemiesData = gameData.getJSONArray("enemies");
        for (Object json : enemiesData) {
            JSONObject enemy = (JSONObject) json;
            addEnemy(g, enemy);
        }
    }

    // MODIFIES: g
    // EFFECTS: parses enemy from JSON object and adds it to game
    private void addEnemy(Game g, JSONObject enemyData) {
        Enemy enemy = new Enemy(enemyData.getInt("cx"), enemyData.getInt("cy"),
                enemyData.getInt("dx"), enemyData.getInt("dy"));
        enemy.setIsRight(enemyData.getBoolean("isRight"));
        g.addEnemy(enemy);
    }

    // MODIFIES: g
    // EFFECTS: parses fireballs from JSON object and adds them to game
    private void addFireballs(Game g, JSONObject gameData) {
        JSONArray fireballsData = gameData.getJSONArray("fireballs");
        for (Object json : fireballsData) {
            JSONObject fireball = (JSONObject) json;
            addFireball(g, fireball);
        }
    }

    // MODIFIES: g
    // EFFECTS: parses fireball from JSON object and adds it to game
    private void addFireball(Game g, JSONObject fireballData) {
        Fireball fireball = new Fireball(fireballData.getInt("cx"), fireballData.getInt("cy"),
                fireballData.getBoolean("isRight"));
        g.addFireball(fireball);
    }
}
