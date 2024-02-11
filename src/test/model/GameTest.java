package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    public Game g;

    @BeforeEach
    public void setup() {
        g = new Game(120, 150);
    }

    @Test
    public void getPlayerXYTest() {
        assertEquals(50, g.getPlayerX());
        assertEquals(100, g.getPlayerY());
    }

    @Test
    public void isPaused() {
        assertFalse(g.isPaused());
        g.pause();
        assertTrue(g.isPaused());
    }

    // TODO: test frozen in the future

    @Test
    public void isEndedTest() {
        assertFalse(g.isEnded());
    }

    @Test
    public void playerWalk() {
        g.playerWalk("left");
        g.tick();
        assertEquals(47, g.getPlayerX());
        g.playerWalk("right");
        g.tick();
        assertEquals(50, g.getPlayerX());
    }

    @Test
    public void playerJumpTest() {
        g.playerJump();
        g.tick();
        assertEquals(93, g.getPlayerY());
    }

    @Test
    public void tick() {
        g.tick();
        assertEquals(50, g.getPlayerX());
        assertEquals(100, g.getPlayerY());
        g.playerWalk("left");
        g.tick();
        assertEquals(47, g.getPlayerX());
    }
}
