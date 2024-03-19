package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    public Player p;

    @BeforeEach
    public void setup() {
        p = new Player(50, 100);
    }

    @Test
    public void fireTest() {
        p.fire();
        assertTrue((p.fire() instanceof Fireball));
        assertEquals(p.getCx(), p.fire().getCx());
        assertEquals(p.getCy(), p.fire().getCy());
        p.setDx(-5);
        p.update(40, 40, 1);
        assertFalse(p.fire().getIsRight());
    }

    @Test
    public void refreshTest() {
        p.setDx(4);
        p.setDy(3);
        p.refresh(1);
        p.update(100, 200, 1);
        assertEquals(50, p.getCx());
        assertEquals(104, p.getCy());
    }

    @Test
    public void loadImgTestSuccessful() {
        // TODO: write test for image loader methods
    }
}
