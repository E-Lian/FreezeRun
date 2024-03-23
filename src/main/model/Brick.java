package model;

import java.awt.*;

import static ui.GraphicsGame.BLOCK_SIZE;

// represents a brick in game, used as obstacles and platforms
public class Brick extends Block {

    // EFFECTS: instantiate a Brick, load img and set bound
    public Brick(int cx, int cy) {
        this.cx = cx;
        this.cy = cy;
        this.width = BLOCK_SIZE;
        this.height = BLOCK_SIZE;
        loadImg();
        makeHitBox();
    }

    // MODIFIES: this
    // EFFECTS: set the bound
    @Override
    public void makeHitBox() {
        this.hitBox = new Rectangle(this.cx, this.cy,
                width, height);
    }

    // MODIFIES: this
    // EFFECTS: load image
    public void loadImg() {
        super.loadImg("./data/img/map/brick.png");
    }

}
