package model;

import java.awt.*;

public class Brick extends Block {

    // EFFECTS: instantiate a Brick, load img and set bound
    public Brick(int cx, int cy) {
        this.cx = cx;
        this.cy = cy;
        this.width = 16;
        this.height = 16;
        loadImg();
        makeHitBox();
    }

    // MODIFIES: this
    // EFFECTS: set the bound
    @Override
    public void makeHitBox() {
        this.hitBox = new Rectangle(this.cx, this.cy,
                scale * width, scale * height);
    }

    // MODIFIES: this
    // EFFECTS: load image
    public void loadImg() {
        super.loadImg("./data/img/map/brick.png");
    }

}
