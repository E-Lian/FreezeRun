package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    public Player p;

    @BeforeEach
    public void setup() {
        p = new Player();
    }

    @Test
    public void refreshTest() {
        p.setDx(-10);
        p.setDy(10);
        p.refresh(5);
        p.update();
        assertEquals(50, p.getCx());
        assertEquals(105, p.getCy());
    }
}
