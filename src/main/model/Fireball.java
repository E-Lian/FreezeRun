package model;

import org.json.JSONObject;
import persistence.Writable;

import java.awt.*;

// represents a fireball shot by the player
public class Fireball extends Block implements Writable {

    private int cx;
    private final int cy;
    private final boolean isRight;
    private final int speed;

    private static final int SPEED = 2;

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
    // EFFECTS: move the fireball
    public void update() {
        this.cx += this.speed;
    }

    // MODIFIES: this
    // EFFECTS: load fireball image
    protected void loadImg() {
        super.loadImg("./data/img/fireball/fireball1.png");
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

    public boolean getIsRight() {
        return isRight;
    }
}
