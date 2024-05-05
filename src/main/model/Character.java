package model;

import org.json.JSONObject;
import persistence.Writable;

// represents objects in game that can move and have some animation
public abstract class Character extends Block implements Writable {

    // dx: negative - left
    // dx: positive - right
    protected int dx;
    // dy: negative - up
    // dy: positive - down
    protected double dy;
    protected boolean isRight;
    protected boolean jumping = false;
    protected boolean falling = true;
    protected int hp;

    protected int imgIndex = 1;

    // EFFECTS: returns current fields as a JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("cx", cx);
        jsonObject.put("cy", cy);
        jsonObject.put("dx", dx);
        jsonObject.put("dy", dy);
        jsonObject.put("isRight", isRight);
        jsonObject.put("hp", hp);
        jsonObject.put("jumping", jumping);
        jsonObject.put("falling", falling);
        return jsonObject;
    }

    // MODIFIES: this
    // EFFECT: move the character by its dx and dy, make the character fall if applicable
    public void update(double gravity) {
        if (jumping && dy <= 0) {
            jumping = false;
            falling = true;
        } else if (jumping) {
            dy -= gravity;
            cy -= dy;
        }

        if (falling) {
            cy += dy;
            dy = dy + gravity;
        }

        cx += dx;

        if (dx < 0) {
            setIsRight(false);
        } else if (dx > 0) {
            setIsRight(true);
        }

        incrementIndex();
    }

    // EFFECT: returns true if hp <= 0
    public boolean isDead() {
        return (hp <= 0);
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public double getDy() {
        return this.dy;
    }

    public int getDx() {
        return this.dx;
    }

    public boolean getIsRight() {
        return isRight;
    }

    public void setIsRight(boolean isRight) {
        this.isRight = isRight;
    }

    public boolean isJumping() {
        return jumping;
    }

    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    // saved for later animation implementation
    public int getImgIndex() {
        return imgIndex;
    }

    public void incrementIndex() {
        imgIndex++;
        if (imgIndex > 30) {
            imgIndex = 1;
        }
    }

}
