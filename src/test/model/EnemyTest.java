package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyTest {
    public Enemy e;

    @BeforeEach
    public void setup() {
        e = new Enemy(0, 0, 5, 6);
    }

    @Test
    public void inBoundInside() {
        e.inBound(40, 40);
        assertEquals(0, e.getCx());
        assertEquals(0, e.getCy());
    }

    @Test
    public void inBoundOutside() {
        e.setDx(100);
        e.setDy(100);
        e.inBound(30, 40);
        e.update(30, 40, 1);
        assertEquals(30, e.getCx());
        assertEquals(40, e.getCy());
        e.update(30, 40, 1);
        assertEquals(0, e.getCx());
        assertEquals(0, e.getCx());
    }
}
