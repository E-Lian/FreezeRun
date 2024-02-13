package model;

// Character is an abstract class that consists
// some common fields and methods that characters
// in a game (enemies, players...) have
public abstract class Character {

    protected int cx;
    protected int cy;
    // dx: negative - left
    // dx: positive - right
    protected int dx;
    // dy: negative - up
    // dy: positive - down
    protected double dy;
    protected char dir;
    protected int hp;

    // MODIFIES: this
    // EFFECT: move the character by its dx and dy
    public void update(int maxX, int maxY, double gravity) {
        cx += dx;
        cy += dy;

        if (this.dx < 0) {
            this.dir = 'l';
        } else if (this.dx > 0) {
            this.dir = 'r';
        }

        inBound(maxX, maxY);
        refresh(gravity);
    }

    // MODIFIES: this
    // EFFECTS: keeps the character in bound
    public void inBound(int maxX, int maxY) {
        if (this.cx < 0) {
            this.cx = 0;
        } else if (this.cx > maxX) {
            this.cx = maxX;
        }

        if (this.cy < 0) {
            this.cy = 0;
        } else if (this.cy > maxY) {
            this.cy = maxY;
            setDy(0);
        }
    }

    // MODIFIES: this
    // EFFECTS: simulates gravity and pulls all characters to the ground
    public void refresh(double gravity) {
        if (this.dy != 0) {
            this.dy += gravity;
        }
    }

    // EFFECT: returns true if hp <= 0
    public boolean isDead() {
        return (hp <= 0);
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public int getCx() {
        return cx;
    }

    public int getCy() {
        return cy;
    }

    public char getDir() {
        return dir;
    }

}
