package model;

import ui.GraphicsGame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;

// represents an object in game that has a rectangular area which can be used for collision detection
public abstract class Block {
    protected BufferedImage img;

    protected Rectangle hitBox;
    protected int scale = GraphicsGame.SCALE;

    protected int cx;
    protected int cy;
    protected int width;
    protected int height;

    // MODIFIES: this
    // EFFECTS: create new Rectangle as this block's hitbox area
    public abstract void makeHitBox();

    public int getCx() {
        return cx;
    }

    public int getCy() {
        return cy;
    }

    public void setCx(int cx) {
        this.cx = cx;
    }

    public void setCy(int cy) {
        this.cy = cy;
    }

    public BufferedImage getImg() {
        return this.img;
    }

    // MODIFIES: this
    // EFFECTS: load this block's image
    protected void loadImg(String source) {
        try {
            img = ImageIO.read(new FileInputStream(source));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // MODIFIES: this
    // EFFECTS: make hitbox based on block's current position and returns it
    public Rectangle getHitBox() {
        makeHitBox();
        return hitBox;
    }

    // EFFECTS: returns the hitbox used for left-side collision detection
    public Rectangle getLeftBox() {
        Rectangle hitBox = getHitBox();
        return new Rectangle((int) hitBox.x, (int) hitBox.y + hitBox.height / 4,
                hitBox.width / 4, hitBox.height / 2);
    }

    // EFFECTS: returns the hitbox used for right-side collision detection
    public Rectangle getRightBox() {
        Rectangle hitBox = getHitBox();
        return new Rectangle((int) hitBox.x + 3 * hitBox.width / 4, (int) hitBox.y + hitBox.height / 4,
                hitBox.width / 4, hitBox.height / 2);
    }

    // EFFECTS: returns the hitbox used for top collision detection
    public Rectangle getTopBox() {
        Rectangle hitBox = getHitBox();
        return new Rectangle((int) hitBox.x + hitBox.width / 6, (int) hitBox.y,
                2 * hitBox.width / 3, hitBox.height / 2);
    }

    // EFFECTS: returns the hitbox used for bottom collision detection
    public Rectangle getBottomBox() {
        Rectangle hitBox = getHitBox();
        return new Rectangle((int) hitBox.x + hitBox.width / 6, (int) hitBox.y + hitBox.height / 2,
                2 * hitBox.width / 3, hitBox.height / 2);
    }
}
