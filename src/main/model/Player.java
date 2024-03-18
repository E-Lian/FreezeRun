package model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.nio.Buffer;

// represents the Player in the game
public class Player extends Character {
    final int playerHP = 3;

    // EFFECTS: instantiate a Player at set position, standing still, facing right, with desired hp
    public Player(int cx, int cy) {
        this.cx = cx;
        this.cy = cy;
        this.dx = 0;
        this.dy = 0;
        this.isRight = true;
        this.hp = playerHP;
        loadImg();
    }

    private void loadImg() {
        try {
            img = ImageIO.read(new FileInputStream("./data/img/player/player_still1.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
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
