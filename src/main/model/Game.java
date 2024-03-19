package model;

import com.googlecode.lanterna.input.KeyType;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.Objects;

import static ui.GraphicsGame.*;

// manages the inside of the game, changing and updating information
// Reference: https://github.students.cs.ubc.ca/CPSC210/SnakeConsole-Lanterna
public class Game implements Writable {

    public static final int TICKS_PER_SECOND = 30;
    // TODO: lots of stuff to tinker here
    private int maxX;
    private int ground;
    private static final double GRAVITY = 1.5;

    private Player player;
    private static final int PLAYER_INIT_X = BLOCK_SIZE;
    private static final int PLAYER_INIT_Y = SCREEN_HEIGHT - 2 * BLOCK_SIZE;
    private static final int PLAYER_SPEED = 4;
    private static final double JUMP_STRENGTH = -7;

    // temp enemy variables for Phase 1
    private ArrayList<Enemy> enemies;
    private static final int ENEMY_SPEED = 1;
    private static final int ENEMY_MAX_X = 70;

    private ArrayList<Fireball> fireballs;

    private long timeOfFreeze;

    private boolean frozen;
    private boolean paused;
    private boolean ended;

    // EFFECTS: instantiates a running Game with a player and enemy
    public Game(int maxX, int ground) {
        this.maxX = maxX;
        this.ground = ground;
        this.player = new Player(PLAYER_INIT_X, PLAYER_INIT_Y);
        this.enemies = new ArrayList<Enemy>();
        this.enemies.add(new Enemy(50, 22, ENEMY_SPEED, 0));
        this.fireballs = new ArrayList<Fireball>();
        this.frozen = false;
        this.paused = false;
        this.ended = false;
    }

    // EFFECTS: put game information into JSON representation and return it
    @Override
    public JSONObject toJson() {
        if (ended) {
            throw new IllegalStateException();
        }
        JSONObject json = new JSONObject();
        json.put("maxX", maxX);
        json.put("ground", ground);
        json.put("frozen", frozen);
        json.put("player", player.toJson());
        json.put("enemies", enemiesToJson());
        json.put("fireballs", fireballsToJson());
        return json;
    }

    // EFFECTS: returns a JSONArray containing fireballs information in current game
    private JSONArray fireballsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Fireball f : fireballs) {
            jsonArray.put(f.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns a JSONArray containing enemies information in current game
    private JSONArray enemiesToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Enemy e : enemies) {
            jsonArray.put(e.toJson());
        }

        return jsonArray;
    }

    // MODIFIES: player
    // EFFECTS: sets the player's dx
    public void playerWalk(boolean isRight) {
        if (isRight) {
            player.setDx(PLAYER_SPEED);
        } else {
            player.setDx(-PLAYER_SPEED);
        }
    }

    // REQUIRES: player.dy == 0
    // MODIFIES: player
    // EFFECTS: make the player jump
    public void playerJump() {
        if (player.getPlayerDy() == 0) {
            player.setDy(JUMP_STRENGTH);
        }
    }

    // MODIFIES: this
    // EFFECTS: create new Fireball and add it to fireballs
    public void playerFire() {
        // store the Fireball created by Player and empty the enemies list
        this.fireballs.add(player.fire());
        this.enemies = new ArrayList<Enemy>();
    }

    // REQUIRES: System.currentTimeMillis() - timeOfFreeze > 80000
    // MODIFIES: this
    // EFFECTS: freeze the game
    public void freeze() {
        if (System.currentTimeMillis() - timeOfFreeze >= 8000) {
            this.frozen = true;
            this.timeOfFreeze = System.currentTimeMillis();
        }
    }

    // MODIFIES: this
    // EFFECTS: progress the game
    public void tick() {
        // update Characters
        player.update(maxX, ground, GRAVITY);

        if (isFrozen()) {
            if (System.currentTimeMillis() - timeOfFreeze >= 3000) {
                this.frozen = false;
            }
            return;
        }

        for (Enemy e : enemies) {
            e.update(ENEMY_MAX_X, ground, GRAVITY);
        }

        for (int i = 0; i < fireballs.size(); i++) {
            fireballs.get(i).update(maxX);
            if (fireballs.get(i).isOutOfBound()) {
                fireballs.remove(i);
                i--;
            }
        }

    }

    public BufferedImage getPlayerImage() {
        return this.player.getImg();
    }

    // MODIFIES: this
    // EFFECTS: removes the first enemy in enemies that was initialised in constructor since the game loads from a file
    public void load() {
        enemies.remove(0);
    }

    // MODIFIES: this
    // EFFECTS: add the given fireball to fireballs
    public void addFireball(Fireball fireball) {
        fireballs.add(fireball);
    }

    // MODIFIES: this
    // EFFECTS: add the given enemy to enemies
    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public boolean isPaused() {
        return paused;
    }

    // MODIFIES: this
    // EFFECTS: switch paused between true and false
    public void pause() {
        paused = !paused;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getGround() {
        return ground;
    }

    public int getPlayerX() {
        return player.getCx();
    }

    public int getPlayerY() {
        return player.getCy();
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public ArrayList<Fireball> getFireballs() {
        return fireballs;
    }

    public boolean isEnded() {
        return ended;
    }

    public boolean isFrozen() {
        return frozen;
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }


}
