package model;

import org.json.JSONObject;
import persistence.Writable;

import java.awt.*;

// a fireball shot by the player
public class Fireball extends Block implements Writable {

    private int cx;
    private final int cy;
    private final boolean isRight;
    private final int speed;
    private boolean hit;
    private boolean outOfBound;

    private static final int SPEED = 1;

    // EFFECTS: instantiates a Fireball at given position facing given direction and sets its speed accordingly
    public Fireball(int cx, int cy, boolean isRight) {
        this.cx = cx;
        this.cy = cy;
        this.width = 11;
        this.height = 7;
        this.isRight = isRight;
        if (!isRight) {
            this.speed = -SPEED;
        } else {
            this.speed = SPEED;
        }
        this.hit = false;
        this.outOfBound = false;
        loadImg();
    }

    // EFFECTS: returns the fireball's fields as a JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("cx", cx);
        jsonObject.put("cy", cy);
        jsonObject.put("isRight", isRight);
        return jsonObject;
    }

    // MODIFIES: this
    // EFFECTS: update the bullet, detect if it hits bounds
    public void update(int maxX) {
        move();
        if (getCx() <= 0 || getCx() >= maxX) {
            outOfBound = true;
        }
    }

    // MODIFIES: this
    // EFFECTS: load fireball image
    protected void loadImg() {
        super.loadImg("./data/img/fireball/fireball1.png");
    }

    // REQUIRES: !isOutOfBound()
    // MODIFIES: this
    // EFFECTS: move the bullet
    public void move() {
        this.cx += this.speed;
    }

    // MODIFIES: this
    // EFFECTS: set the bound
    @Override
    public void makeHitBox() {
        this.hitBox = new Rectangle(this.cx + scale * 4, this.cy + scale * 4,
                scale * width, scale * height);
    }

    public int getCx() {
        return cx;
    }

    public int getCy() {
        return cy;
    }

    @Override
    public Rectangle getLeftBox() {
        return null;
    }

    @Override
    public Rectangle getRightBox() {
        return null;
    }

    @Override
    public Rectangle getTopBox() {
        return null;
    }

    @Override
    public Rectangle getBottomBox() {
        return null;
    }

    public boolean isHit() {
        return hit;
    }

    public boolean isOutOfBound() {
        return outOfBound;
    }

    public boolean getIsRight() {
        return isRight;
    }
}
