package model;

import ui.GraphicsGame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;

public abstract class Block {
    protected BufferedImage img;

    protected Rectangle hitBox;
    protected int scale = GraphicsGame.SCALE;

    protected int cx;
    protected int cy;
    protected int width;
    protected int height;
    protected int hp;

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

    protected void loadImg(String source) {
        try {
            img = ImageIO.read(new FileInputStream(source));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Rectangle getHitBox() {
        makeHitBox();
        return hitBox;
    }

    public abstract Rectangle getLeftBox();

    public abstract Rectangle getRightBox();

    public abstract Rectangle getTopBox();

    public abstract Rectangle getBottomBox();
}
