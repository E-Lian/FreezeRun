package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    public Game g;

    @BeforeEach
    public void setup() {
        g = new Game(120, 120);
    }

    @Test
    public void playerWalkLeft() {
        g.playerWalk(false);
        g.tick();
        assertEquals(28, g.getPlayerX());
    }

    @Test
    public void playerWalkRight() {
        g.playerWalk(true);
        g.tick();
        assertEquals(36, g.getPlayerX());
    }

    @Test
    public void playerJump() {
        g.playerJump();
        g.tick();
        assertEquals(120, g.getPlayerY());
    }

    @Test
    public void playerFire() {
        g.playerFire();
        assertEquals(1, g.getFireballs().size());
        assertTrue(g.getEnemies().isEmpty());
    }

    @Test
    public void getPlayerXYTest() {
        assertEquals(32, g.getPlayerX());
        assertEquals(448, g.getPlayerY());
    }

    @Test
    public void getEnemiesTest() {
        assertEquals(1, g.getEnemies().size());
        assertEquals(50, g.getEnemies().get(0).getCx());
        assertEquals(22, g.getEnemies().get(0).getCy());
    }

    @Test
    public void getFireballs() {
        assertTrue(g.getFireballs().isEmpty());
        g.playerFire();
        assertEquals(1, g.getFireballs().size());
        g.playerFire();
        assertEquals(2, g.getFireballs().size());
    }

    @Test
    public void isPausedTest() {
        assertFalse(g.isPaused());
    }

    @Test
    public void pauseTest() {
        assertFalse(g.isPaused());
        g.pause();
        assertTrue(g.isPaused());
        g.pause();
        assertFalse(g.isPaused());
    }

    @Test
    public void isEnded() {
        assertFalse(g.isEnded());
    }

    @Test
    public void tickFireballTest() {
        g = new Game(52, 52);
        g.playerFire();
        g.tick();
        assertEquals(1, g.getFireballs().size());
        g.tick();
        assertFalse(g.getFireballs().isEmpty());
    }

    @Test
    public void tickFrozenTest() throws InterruptedException {
        g.freeze();
        g.tick();
        assertEquals(32, g.getPlayerX());
        assertEquals(120, g.getPlayerY());
        assertEquals(50, g.getEnemies().get(0).getCx());
        assertEquals(22, g.getEnemies().get(0).getCy());
        Thread.sleep(4000L);
        g.tick();
        assertFalse(g.isFrozen());
        g.tick();
        assertEquals(51, g.getEnemies().get(0).getCx());
        assertEquals(22, g.getEnemies().get(0).getCy());
    }

    @Test
    public void isFrozenTest() {
        assertFalse(g.isFrozen());
        g.freeze();
        assertTrue(g.isFrozen());
    }

    @Test
    public void freezeTest() throws InterruptedException {
        assertFalse(g.isFrozen());
        g.freeze();
        assertTrue(g.isFrozen());
        Thread.sleep(3000L);
        g.tick();
        assertFalse(g.isFrozen());
        setup();
        g.freeze();
        Thread.sleep(4000L);
        g.freeze();
        g.tick();
        assertFalse(g.isFrozen());
        Thread.sleep(5000L);
        g.freeze();
        g.tick();
        assertTrue(g.isFrozen());
    }
}
