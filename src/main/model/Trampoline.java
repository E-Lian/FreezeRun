package model;

import java.awt.*;

import static ui.GraphicsGame.BLOCK_SIZE;

// represents a trampoline in game
public class Trampoline extends Item {

    // EFFECTS: instantiates a trampoline at given location and set its period
    public Trampoline(int cx, int cy) {
        super(cx, cy);
        this.width = BLOCK_SIZE;
        this.height = 16;
        this.period = 500;
    }

    // MODIFIES: this
    // EFFECTS: makes the hitbox according to activated
    @Override
    public void makeHitBox() {
        if (!activated) {
            this.hitBox = new Rectangle(this.cx, this.cy, width, height);
        } else {
            this.hitBox = new Rectangle(this.cx, this.cy, width, BLOCK_SIZE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the image
    @Override
    public void loadImg() {
        if (activated) {
            super.loadImg("./data/img/map/trampoline_activated.png");
        } else {
            super.loadImg("./data/img/map/trampoline_still.png");
        }
    }

}
