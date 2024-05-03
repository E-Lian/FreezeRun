package persistence;

import model.Enemy;
import model.Fireball;
import model.Game;
import model.Player;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads game data from JSON file
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
        Game game = new Game(gameData.getInt("maxX"), gameData.getInt("ground"));
        game.pause();
        game.setLevel(gameData.getInt("levelNum"));
        game.setFrozen(gameData.getBoolean("frozen"));
        addPlayer(game, gameData);
        addFireballs(game, gameData);
        addEnemies(game, gameData);
        game.load();

        return game;
    }

    // MODIFIES: g
    // EFFECTS: parses player from JSON object and adds it to game
    private void addPlayer(Game g, JSONObject gameData) {
        JSONObject playerData = gameData.getJSONObject("player");
        Player player = new Player(0, 0);
        player.setDx(playerData.getInt("dx"));
        player.setDy(playerData.getInt("dy"));
        player.setCx(playerData.getInt("cx"));
        player.setCy(playerData.getInt("cy"));
        player.setIsRight(playerData.getBoolean("isRight"));

        g.setPlayer(player);
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
