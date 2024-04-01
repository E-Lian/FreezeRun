package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    public Player player;

    @BeforeEach
    public void setup() {
        player = new Player(100, 100);
    }

    @Test
    public void testConstructor() {
        assertEquals(100, player.getCx());
        assertEquals(100, player.getCy());
        assertEquals(0, player.getDx());
        assertEquals(0, player.getDy());
        assertFalse(player.isJumping());
        assertTrue(player.isFalling());
        assertFalse(player.isDead());
        assertTrue(player.getIsRight());
    }

    @Test
    public void testJumpFalling() {
        player.setFalling(true);
        player.jump();
        assertEquals(0, player.getDy());
        assertFalse(player.isJumping());
        assertTrue(player.isFalling());
    }

    @Test
    public void testJumpJumping() {
        player.setJumping(true);
        player.jump();
        assertEquals(0, player.getDy());
        assertTrue(player.isJumping());
        assertTrue(player.isFalling());
    }

    @Test
    public void testJumpCanJump() {
        player.setJumping(false);
        player.setFalling(false);
        player.jump();
        assertEquals(-15, player.getDy());
        assertTrue(player.isJumping());
    }

    @Test
    public void testFire() {
        Fireball f = player.fire();
        assertTrue(f.getIsRight());
        assertEquals(100, f.getCx());
        assertEquals(100, f.getCy());
    }

    @Test
    public void decreasePlayerHp() {
        assertFalse(player.isDead());
        player.decreasePlayerHp();
        player.decreasePlayerHp();
        player.decreasePlayerHp();
        assertTrue(player.isDead());
    }

    @Test
    public void testHitBox() {
        Rectangle hitBox = player.getHitBox();
        assertEquals(108, hitBox.getX());
        assertEquals(102, hitBox.getY());
        assertEquals(20, hitBox.getWidth());
        assertEquals(30, hitBox.getHeight());
    }
}
