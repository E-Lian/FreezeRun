package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

// manages the inside of the game, changing and updating information
// Reference: https://github.students.cs.ubc.ca/CPSC210/SnakeConsole-Lanterna
public class Game implements Writable {
    // TODO: - player save when game has ended

    public static final int TICKS_PER_SECOND = 30;
    private int maxX;
    private int ground;
    public static final double GRAVITY = 1.2;

    private ArrayList<Block> blocks;

    private Player player;
    private static final int PLAYER_SPEED = 4;

    // temp enemy variables for Phase 1
    private ArrayList<Enemy> enemies;
    public static final int ENEMY_SPEED = 1;
    private static final int ENEMY_MAX_X = 70;

    private ArrayList<Fireball> fireballs;

    private CollisionChecker collisionChecker;

    private long timeOfFreeze;

    private boolean frozen;
    private boolean paused;
    private boolean ended;

    // EFFECTS: instantiates a running Game with a player and enemy
    public Game(int maxX, int ground) {
        this.maxX = maxX;
        this.ground = ground;
        this.enemies = new ArrayList<Enemy>();
        this.fireballs = new ArrayList<Fireball>();
        this.frozen = false;
        this.paused = false;
        this.ended = false;
        this.collisionChecker = new CollisionChecker();
        Level level = new Level(this);
        this.blocks = level.realizeMap();
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

    // MODIFIES: player
    // EFFECTS: make the player jump
    public void playerJump() {
        player.jump();
    }

    // MODIFIES: this
    // EFFECTS: create new Fireball and add it to fireballs
    public void playerFire() {
        addFireball(player.fire());
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
        player.update(GRAVITY);
        player.setDx(0);

        if (isFrozen()) {
            if (System.currentTimeMillis() - timeOfFreeze >= 3000) {
                this.frozen = false;
            }
            return;
        }

        for (Enemy e : enemies) {
            e.update(GRAVITY);
        }

        for (Fireball fireball : fireballs) {
            fireball.update();
        }

        // check for collisions
        checkCollisions();
    }

    // TODO: player stops moving after firing

    // MODIFIES: this
    // EFFECTS: check all types of collisions
    public void checkCollisions() {
        // TODO: - enemy collision with player
        //  - player collision with fireballs

        // check player's collisions with blocks
        collisionChecker.checkBlockCollision(player, blocks);
        // check enemies' collisions with fireballs, player, and blocks
        for (int i = 0; i < enemies.size(); i++) {
            Fireball f = collisionChecker.checkEnemyFireballCollision(enemies.get(i), fireballs);
            if (f != null) {
                enemies.remove(i);
                i--;
                fireballs.remove(f);
            }
            if (collisionChecker.checkEnemyPlayerCollision(enemies.get(i), getPlayer())) {
                player.decreasePlayerHp();
            }
            collisionChecker.checkBlockCollision(enemies.get(i), blocks);

        }
        // check fireballs' collisions with blocks
        for (int i = 0; i < fireballs.size(); i++) {
            if (collisionChecker.checkFireballBlocksCollision(fireballs.get(i), blocks)) {
                fireballs.remove(i);
                i--;
            }
        }
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

    public void setEnded(boolean ended) {
        this.ended = ended;
    }

    public boolean isFrozen() {
        return frozen;
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    // TODO: used for testing, delete later
    public Player getPlayer() {
        return player;
    }
}
