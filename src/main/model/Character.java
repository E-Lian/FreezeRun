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
    protected int hp;


    // MODIFIES: this
    // EFFECT: move the character by its dx and dy
    public void update() {
        // TODO: Characters cannot be out of bound
        cx += dx;
        cy += dy;
    }

    // MODIFIES: this
    // EFFECTS: keeps the character in bound
    public void inBound(int maxX, int maxY) {
        if (cx > maxX) {
            cx = maxX;
        } else if (cx < 0) {
            cx = 0;
        }

        if (cy > maxY) {
            cy = maxY;
        } else if (cy < 0) {
            cy = 0;
        }
    }

    // MODIFIES: this
    // EFFECTS: simulates gravity and pulls all characters to the ground
    public void refresh(double gravity) {
        this.dy += gravity;
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

}
