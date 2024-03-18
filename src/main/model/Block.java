package model;

import java.awt.image.BufferedImage;

public abstract class Block {
    protected BufferedImage img;

    protected int cx;
    protected int cy;
    protected int hp;

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
}
