package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {
    public Character c;

    @BeforeEach
    public void setup() {
        c = new Enemy(1, 2, 1, 2);
    }

    @Test
    public void gettersTest() {
        assertEquals(1, c.getCx());
        assertEquals(2, c.getCy());
    }

    @Test
    public void settersTest() {
        c.setDx(-1);
        c.setDy(-2);
        c.update();
        assertEquals(0, c.getCx());
        assertEquals(0, c.getCy());
    }

    @Test
    public void updateTest() {
        c.update();
        assertEquals(2, c.getCx());
        assertEquals(4, c.getCy());
    }

    @Test
    public void inBoundInside() {
        c.update();
        assertEquals(2, c.getCx());
        assertEquals(4, c.getCy());
        c.inBound(5, 5);
        assertEquals(2, c.getCx());
        assertEquals(4, c.getCy());
    }

    @Test
    public void inBoundOutside1() {
        c.update();
        assertEquals(2, c.getCx());
        assertEquals(4, c.getCy());
        c.inBound(1, 1);
        assertEquals(1, c.getCx());
        assertEquals(1, c.getCy());
    }

    @Test
    public void inBoundOutside2() {
        c = new Enemy(1, 2, -4, -4);
        c.update();
        c.inBound(4, 4);
        assertEquals(0, c.getCx());
        assertEquals(0, c.getCy());
    }

    @Test
    public void refreshTest() {
        c.refresh(1);
        c.update();
        assertEquals(3, c.getCy());
    }
}