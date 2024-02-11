package model;

import java.util.ArrayList;

// manages the inside of the game, changing and updating information
public class Game {

    public static final int TICKS_PER_SECOND = 30;
    private final int maxX;
    private final int maxY;
    private static final double GRAVITY = 1.5;

    private Player player;
    private ArrayList<Enemy> enemies;
    private static final int PLAYER_SPEED = 3;
    private static final double JUMP_STRENGTH = -7;
    private static final int ENEMY_SPEED = 3;

    private boolean paused;
    private boolean ended;

    public Game(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
        this.player = new Player();
    }

    // EFFECTS: returns the player's x
    public int getPlayerX() {
        return player.getCx();
    }

    // EFFECTS: returns the player's y
    public int getPlayerY() {
        return player.getCy();
    }

    // EFFECTS: returns true if game has ended, false otherwise
    public boolean isEnded() {
        return ended;
    }

    // MODIFIES: this
    // EFFECTS: sets the player's dx
    public void playerWalk(String dir) {
        if (dir == "left") {
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
        // TODO: enemies tick

        // update Characters
        player.update();
        player.inBound(maxX, maxY);

//        for (Enemy e: enemies) {
//            e.update();
//        }

        // refresh info
        player.refresh(GRAVITY);
    }
}
