package model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.FileInputStream;

public class Brick extends Block {

    // EFFECTS: instantiate a Brick, load img and set bound
    public Brick(int cx, int cy) {
        this.cx = cx;
        this.cy = cy;
        loadImg();
        makeBound();
    }

    // MODIFIES: this
    // EFFECTS: set the bound
    @Override
    public void makeBound() {
        this.bound = new Rectangle(this.cx, this.cy, scale * 16, scale * 16);
    }

    // MODIFIES: this
    // EFFECTS: load image
    public void loadImg() {
        super.loadImg("./data/img/map/brick.png");
    }

}
