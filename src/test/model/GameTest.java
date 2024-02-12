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
        g.playerWalk('l');
        g.tick();
        assertEquals(46, g.getPlayerX());
    }

    @Test
    public void playerWalkRight() {
        g.playerWalk('r');
        g.tick();
        assertEquals(54, g.getPlayerX());
    }

    @Test
    public void playerJump() {
        g.playerJump();
        g.tick();
        assertEquals(93, g.getPlayerY());
    }

    @Test
    public void playerFire() {
        g.playerFire();
        assertEquals(1, g.getFireballs().size());
        assertTrue(g.getEnemies().isEmpty());
    }

    @Test
    public void getPlayerXYTest() {
        assertEquals(50, g.getPlayerX());
        assertEquals(100, g.getPlayerY());
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
        assertTrue(g.getFireballs().isEmpty());
    }

    // TODO: isFrozen test
}
