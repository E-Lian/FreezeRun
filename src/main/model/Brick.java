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

    @Override
    public Rectangle getLeftBox() {
        Rectangle hitBox = getHitBox();
        return new Rectangle(getCx(), getCy(), 6, (int) hitBox.getHeight());
    }

    @Override
    public Rectangle getRightBox() {
        Rectangle hitBox = getHitBox();
        return new Rectangle(getCx() + 8, getCy(), 6, (int) hitBox.getHeight());
    }

    @Override
    public Rectangle getTopBox() {
        Rectangle hitBox = getHitBox();
        return new Rectangle(getCx() + 6, getCy(), 2, (int) (hitBox.getHeight() / 2));
    }

    @Override
    public Rectangle getBottomBox() {
        Rectangle hitBox = getHitBox();
        return new Rectangle(getCx() + 6, (int) (getCy() + hitBox.getHeight() / 2),
                2, (int) (hitBox.getHeight() / 2));
    }

    // MODIFIES: this
    // EFFECTS: load image
    public void loadImg() {
        super.loadImg("./data/img/map/brick.png");
    }

}
