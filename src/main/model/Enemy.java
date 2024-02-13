package model;

// represents an Enemy in the game
public class Enemy extends Character {

    // EFFECTS: instantiates an Enemy object wit hp of 1 and facing right
    public Enemy(int cx, int cy, int dx, int dy) {
        this.cx = cx;
        this.cy = cy;
        this.dx = dx;
        this.dy = dy;
        this.hp = 1;
        this.dir = 'r';
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
