package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static ui.GraphicsGame.BLOCK_SIZE;


public class DoorTest {
    public Door door;

    @BeforeEach
    public void setup() {
        door = new Door(100, 100);
    }

    @Test
    public void testConstructor() {
        assertEquals(100, door.getCx());
        assertEquals(100, door.getCy());
        Rectangle hitBox = door.getHitBox();
        assertEquals(BLOCK_SIZE, hitBox.getWidth());
        assertEquals(BLOCK_SIZE, hitBox.getHeight());
        assertFalse(door.getOpen());
    }

    @Test
    public void testOpen() {
        door.setOpen(true);
        assertTrue(door.getOpen());
        door.setOpen(false);
        assertFalse(door.getOpen());
    }
}
