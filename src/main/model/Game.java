package model;

import java.util.ArrayList;
import java.util.Objects;

// manages the inside of the game, changing and updating information
public class Game {

    public static final int TICKS_PER_SECOND = 30;
    private final int maxX;
    private final int maxY;
    private static final double GRAVITY = 1.5;

    private Player player;
    private static final int PLAYER_SPEED = 4;
    private static final double JUMP_STRENGTH = -7;

    // temp enemy variables for Phase 1
    private ArrayList<Enemy> enemies;
    private static final int ENEMY_SPEED = 1;
    private static final int ENEMY_MAX_X = 70;

    private boolean frozen;
    private boolean paused;
    private boolean ended;

    public Game(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
        this.player = new Player();
        this.enemies = new ArrayList<Enemy>();
        this.enemies.add(new Enemy(50, 22, ENEMY_SPEED, 0));
        this.frozen = false;
        this.paused = false;
        this.ended = false;
    }

    // MODIFIES: this
    // EFFECTS: sets the player's dx
    public void playerWalk(String dir) {
        if (Objects.equals(dir, "left")) {
            player.setDx(-PLAYER_SPEED);
        } else {
            player.setDx(PLAYER_SPEED);
        }
    }

    // MODIFIES: this
    // EFFECTS: make the player jump
    public void playerJump() {
        player.setDy(JUMP_STRENGTH);
    }

    // MODIFIES: this
    // EFFECTS: progress the game
    public void tick() {
        // update Characters
        player.update();
        player.inBound(maxX, maxY);

        for (Enemy e : enemies) {
            e.update();
            e.inBound(ENEMY_MAX_X, maxY);
            e.refresh(GRAVITY);
        }

        // refresh info
        player.refresh(GRAVITY);
    }

    public int getPlayerX() {
        return player.getCx();
    }

    public int getPlayerY() {
        return player.getCy();
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public boolean isPaused() {
        return paused;
    }

    public void pause() {
        paused = !paused;
    }

    public boolean isEnded() {
        return ended;
    }

    public boolean isFrozen() {
        return frozen;
    }
}
