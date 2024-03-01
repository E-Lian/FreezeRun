package model;

import org.json.JSONObject;
import persistence.Writable;

// a fireball shot by the player
public class Fireball implements Writable {

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
        this.isRight = isRight;
        if (!isRight) {
            this.speed = -SPEED;
        } else {
            this.speed = SPEED;
        }
        this.hit = false;
        this.outOfBound = false;
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

    // REQUIRES: !isOutOfBound()
    // MODIFIES: this
    // EFFECTS: move the bullet
    public void move() {
        this.cx += this.speed;
    }

    public int getCx() {
        return cx;
    }

    public int getCy() {
        return cy;
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
