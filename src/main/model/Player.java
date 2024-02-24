package model;

// represents the Player in the game
public class Player extends Character {
    final int playerX = 50;
    final int playerY = 100;
    final int playerHP = 3;

    // EFFECTS: instantiate a Player at set position, standing still, facing right, with desired hp
    public Player() {
        this.cx = playerX;
        this.cy = playerY;
        this.dx = 0;
        this.dy = 0;
        this.isRight = true;
        this.hp = playerHP;
    }

    // EFFECTS: create a new Fireball at player's position, and returns the Fireball
    public Fireball fire() {
        return new Fireball(getCx(), getCy(), getIsRight());
    }

    // MODIFIES: this
    // EFFECTS: reset the player's dx and dy
    @Override
    public void refresh(double gravity) {
        this.dx = 0;
        super.refresh(gravity);
    }

    public double getPlayerDy() {
        return this.dy;
    }
}
