package model;

// represents an Enemy in the game
public class Enemy extends Character {
    public Enemy(int cx, int cy, int dx, int dy) {
        this.cx = cx;
        this.cy = cy;
        this.dx = dx;
        this.dy = dy;
        this.hp = 1;
    }

    // MODIFIES: this
    // EFFECTS: change direction when touching bounds
    @Override
    public void inBound(int maxX, int maxY) {
        if (cx > maxX) {
            cx = maxX;
            dx = -dx;
        } else if (cx < 0) {
            cx = 0;
            dx = -dx;
        }

        if (cy > maxY) {
            cy = maxY;
        } else if (cy < 0) {
            cy = 0;
        }
    }
}
