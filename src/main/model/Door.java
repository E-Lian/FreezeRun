package model;


import java.awt.*;

import static ui.GraphicsGame.BLOCK_SIZE;

// represents a door in game
public class Door extends Block {

    // EFFECTS: initiates a door at given coordinates
    public Door(int cx, int cy) {
        this.cx = cx;
        this.cy = cy;
        this.width = BLOCK_SIZE;
        this.height = BLOCK_SIZE;
        loadImg();
        makeHitBox();
    }

    // MODIFIES: this
    // EFFECTS: makes hitbox
    @Override
    public void makeHitBox() {
        this.hitBox = new Rectangle(this.cx, this.cy, width, height);
    }

    // MODIFIES: this
    // EFFECTS: load door image
    public void loadImg() {
        super.loadImg("./data/img/map/door_closed.png");
    }
}
