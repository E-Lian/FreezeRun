package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CharacterTest {

    public Character c;

    @BeforeEach
    public void setup() {
        c = new Enemy(1, 2, 0, 2);
    }

    @Test
    public void isDeadTest() {
        assertFalse(c.isDead());
    }

    @Test
    public void getCxyTest() {
        assertEquals(1, c.getCx());
        assertEquals(2, c.getCy());
    }

    @Test
    public void getDirTest() {
        assertEquals('r', c.getDir());
    }

    @Test
    public void setDxyTest() {
        c.setDx(-1);
        c.setDy(-2);
        c.update(10, 10, 1);
        assertEquals(0, c.getCx());
        assertEquals(0, c.getCx());
    }

    // TODO: (2) tests for inBound
    // TODO: tests for refresh



}
