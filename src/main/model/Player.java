package model;

import java.awt.*;

// represents the Player in the game
public class Player extends Character {
    final int playerHP = 3;

    // EFFECTS: instantiate a Player at set position, standing still, facing right, with desired hp
    public Player(int cx, int cy) {
        this.cx = cx;
        this.cy = cy;
        this.width = 10;
        this.height = 15;
        this.dx = 0;
        this.dy = 0;
        this.isRight = true;
        this.hp = playerHP;
        loadImg();
    }

    // MODIFIES: this
    // EFFECTS: load player's image
    private void loadImg() {
        super.loadImg("./data/img/player/player_still1.png");
    }

    // EFFECTS: create a new Fireball at player's position, and returns the Fireball
    public Fireball fire() {
        return new Fireball(getCx(), getCy(), getIsRight());
    }


    public double getPlayerDy() {
        return this.dy;
    }

    // MODIFIES: this
    // EFFECTS: set the bound
    @Override
    public void makeHitBox() {
        this.hitBox = new Rectangle(this.cx + scale * 4, this.cy + scale,
                width * scale, height * scale);
    }

    @Override
    public Rectangle getLeftBox() {
        Rectangle hitBox = getHitBox();
        return new Rectangle((int) hitBox.getX(), (int) hitBox.getY(), 4, height);
    }

    @Override
    public Rectangle getRightBox() {
        Rectangle hitBox = getHitBox();
        return new Rectangle((int) (hitBox.getX() + 6), (int) hitBox.getY(),
                4, height);
    }

    @Override
    public Rectangle getTopBox() {
        Rectangle hitBox = getHitBox();
        return new Rectangle((int) (hitBox.getX() + 4), (int) hitBox.getY(), 2, height / 2);
    }

    @Override
    public Rectangle getBottomBox() {
        Rectangle hitBox = getHitBox();
        return new Rectangle((int) (hitBox.getX() + 4), (int) (hitBox.getY() + height / 2),
                2, (int) (hitBox.getHeight() / 2));
    }
}
