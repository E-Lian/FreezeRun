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
    public void getIsRightTest() {
        assertTrue(c.getIsRight());
    }

    @Test
    public void setDxyTest() {
        c.setDx(-1);
        c.setDy(-2);
        c.update(10, 10, 1);
        assertEquals(0, c.getCx());
        assertEquals(0, c.getCx());
    }

    @Test
    public void inBoundInside() {
        c = new Player();
        c.inBound(100, 100);
        assertEquals(50, c.getCx());
        assertEquals(100, c.getCy());
    }

    @Test
    public void inBoundOutside1() {
        c = new Player();
        c.setDx(100);
        c.setDy(100);
        c.update(50, 50, 1);
        c.inBound(50, 50);
        assertEquals(50, c.getCx());
        assertEquals(50, c.getCy());
    }

    @Test
    public void inBoundOutside2() {
        c = new Player();
        c.setDx(-100);
        c.setDy(-200);
        c.update(40, 40, 1);
        c.inBound(40, 40);
        assertEquals(0, c.getCx());
        assertEquals(0, c.getCy());
    }

    @Test
    public void refreshTest() {
        c.setDy(6);
        c.refresh(4);
        c.update(40, 40, 4);
        assertEquals(12, c.getCy());
    }

    @Test
    public void updateTestDir() {
        assertTrue(c.getIsRight());
        c.setDx(-5);
        c.update(50, 50, 1);
        assertFalse(c.getIsRight());
        c.setDx(10);
        c.update(50, 50, 1);
        assertTrue(c.getIsRight());
    }

    @Test
    public void updateInside() {
        c.update(50, 50, 1);
        assertEquals(1, c.getCx());
        assertEquals(4, c.getCy());
    }

    @Test
    public void updateOutside1() {
        c.setDx(100);
        c.setDy(90);
        c.update(50, 50, 1);
        assertEquals(50, c.getCx());
        assertEquals(50, c.getCy());
    }

    @Test
    public void updateOutside2() {
        c.setDx(-100);
        c.setDy(-90);
        c.update(50, 50, 1);
        assertEquals(0, c.getCx());
        assertEquals(0, c.getCy());
    }

    @Test
    public void updateGravityTest() {
        c.setDy(6);
        c.update(40, 40, 4);
        assertEquals(8, c.getCy());
    }
}
