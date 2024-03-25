package model;

import java.awt.*;

// represents an Enemy in the game
public class Enemy extends Character {
    // EFFECTS: instantiates an Enemy object wit hp of 1 and facing right
    public Enemy(int cx, int cy, int dx, int dy) {
        this.cx = cx;
        this.cy = cy;
        this.width = 14;
        this.height = 15;
        this.dx = dx;
        this.dy = dy;
        this.hp = 1;
        this.isRight = true;
        loadImg();
    }

    // MODIFIES: this
    // EFFECTS: loads the enemy image
    private void loadImg() {
        super.loadImg("./data/img/enemy/enemy_still1.png");
    }

    // MODIFIES: this
    // EFFECTS: set the bound
    @Override
    public void makeHitBox() {
        this.hitBox = new Rectangle(this.cx + scale * 3, this.cy + scale * 2,
                scale * width, scale * height);
    }
}