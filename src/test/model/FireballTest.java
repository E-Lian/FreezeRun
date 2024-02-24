package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FireballTest {

    public Fireball f;

    @BeforeEach
    public void setup() {
        f = new Fireball(10, 10, true);
    }

    @Test
    public void leftFireball() {
        f = new Fireball(10, 10, false);
        f.update(20);
        assertEquals(9, f.getCx());
    }

    @Test
    public void getIsRightTest() {
        assertTrue(f.getIsRight());
    }

    @Test
    public void isOutOfBoundTest() {
        assertFalse(f.isOutOfBound());
    }

    @Test
    public void getCxyTest() {
        assertEquals(10, f.getCx());
        assertEquals(10, f.getCy());
    }

    @Test
    public void isHitTest() {
        assertFalse(f.isHit());
    }

    @Test
    public void move() {
        f.move();
        assertEquals(11, f.getCx());
        f = new Fireball(10, 10, false);
        f.move();
        assertEquals(9, f.getCx());
    }

    @Test
    public void updateInside() {
        f.update(20);
        assertEquals(11, f.getCx());
        assertFalse(f.isOutOfBound());
    }

    @Test
    public void updateOutside1() {
        f.update(10);
        assertEquals(11, f.getCx());
        assertTrue(f.isOutOfBound());
    }

    @Test
    public void updateOutside2() {
        f = new Fireball(1, 5, false);
        f.update(10);
        assertEquals(0, f.getCx());
        assertTrue(f.isOutOfBound());
    }
}
