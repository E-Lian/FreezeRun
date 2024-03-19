package model;

import ui.GraphicsGame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.FileInputStream;

// represents an Enemy in the game
public class Enemy extends Character {
    // EFFECTS: instantiates an Enemy object wit hp of 1 and facing right
    public Enemy(int cx, int cy, int dx, int dy) {
        this.cx = cx;
        this.cy = cy;
        this.dx = dx;
        this.dy = dy;
        this.hp = 1;
        this.isRight = true;
        loadImg();
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

    // MODIFIES: this
    // EFFECTS: loads the enemy image
    private void loadImg() {
        super.loadImg("./data/img/enemy/enemy_still1.png");
    }

    // MODIFIES: this
    // EFFECTS: set the bound
    @Override
    public void makeBound() {
        this.bound = new Rectangle(this.cx + scale * 3, this.cy + scale * 2,
                scale * 14, scale * 9);
    }
}