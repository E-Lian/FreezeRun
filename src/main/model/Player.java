package model;

import java.awt.*;

// represents the Player in the game
public class Player extends Character {
    final int playerHP = 3;
    final double jumpStrength = 15;

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
    // EFFECTS: make the player jump if applicable
    public void jump() {
        if (!isJumping() && !isFalling()) {
            setJumping(true);
            setDy(-jumpStrength);
        }
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

    // MODIFIES: this
    // EFFECTS: make player's hitbox
    @Override
    public void makeHitBox() {
        this.hitBox = new Rectangle(this.cx + scale * 4, this.cy + scale,
                width * scale, height * scale);
    }

    // MODIFIES: this
    // EFFECTS: decrease hp by 1
    public void decreasePlayerHp() {
        this.hp--;
    }

}
