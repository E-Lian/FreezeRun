package model;

// represents the Player in the game
public class Player extends Character {
    final int playerX = 50;
    final int playerY = 100;
    final int playerHP = 3;

    // set Player's initial values
    public Player() {
        this.cx = playerX;
        this.cy = playerY;
        this.dx = 0;
        this.dy = 0;
        this.hp = playerHP;
    }

    // MODIFIES: this
    // EFFECTS: reset the player's dx and dy
    @Override
    public void refresh(double gravity) {
        this.dx = 0;
        super.refresh(gravity);
    }
}
