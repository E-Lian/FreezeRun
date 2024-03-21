package model;

import org.json.JSONObject;
import persistence.Writable;

import java.awt.*;

// represents blocks in game that can move and have some animation
public abstract class Character extends Block implements Writable {

    // dx: negative - left
    // dx: positive - right
    protected int dx;
    // dy: negative - up
    // dy: positive - down
    protected double dy;
    protected boolean isRight;

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
        return jsonObject;
    }

    // MODIFIES: this
    // EFFECT: move the character by its dx and dy
    public void update(int maxX, int maxY, double gravity) {
        cx += dx;
        cy += dy;

        if (this.dx < 0) {
            this.isRight = false;
        } else if (this.dx > 0) {
            this.isRight = true;
        }
    }

    // EFFECTS: return 1 if this collides with given BLock from up,
    // 2 if from underneath, 3 if horizontally, 0 if they don't collide
    public int collisionCheck(Block b) {
        Rectangle r1 = getHitBox();
        Rectangle r2 = b.getHitBox();
        // TODO: finish collision detection
        double bottom2 = r2.getY() + r2.getHeight();
        double right2 = r2.getX() + r2.getWidth();
//        if (!r1.intersects(r2)) {
//             return 0;
//        } else {
//            if ()
//        }
        return 0;
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

}
