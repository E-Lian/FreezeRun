package model;

// a fireball shot by the player
public class Fireball {
    private int cx;
    private int cy;
    private char dir;
    private int speed;
    private boolean hit;
    private boolean outOfBound;
    private static final int SPEED = 1;

    public Fireball(int cx, int cy, char dir) {
        this.cx = cx;
        this.cy = cy;
        this.dir = dir;
        if (dir == 'l') {
            this.speed = -SPEED;
        } else {
            this.speed = SPEED;
        }
        this.hit = false;
        this.outOfBound = false;
    }

    // MODIFIES: this
    // EFFECTS: update the bullet, detect if it hits bounds or the enemy
    public void update(int maxX) {
        move();
        if (getCx() <= 0 || getCx() >= maxX) {
            outOfBound = true;
        }
    }

    // REQUIRES: !isOutOfBound()
    // MODIFIES: this
    // EFFECTS: move the bullet
    public void move() {
        this.cx += this.speed;
    }

    public int getCx() {
        return cx;
    }

    public int getCy() {
        return cy;
    }

    public boolean isHit() {
        return hit;
    }

    public boolean isOutOfBound() {
        return outOfBound;
    }

    public char getDir() {
        return dir;
    }
}
