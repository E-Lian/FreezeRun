package model;

// represents an Enemy in the game
public class Enemy extends Character {
    public Enemy(int cx, int cy, int dx, int dy) {
        this.cx = cx;
        this.cy = cy;
        this.dx = dx;
        this.dy = dy;
        this.hp = 1;
    }
}
