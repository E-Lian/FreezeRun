package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static ui.GraphicsGame.BLOCK_SIZE;

public class BrickTest {
    public Brick brick;

    @BeforeEach
    public void setup() {
        brick = new Brick(0, 0);
    }

    @Test
    public void testMakeHitBox() {
        Rectangle hitBox = brick.getHitBox();
        assertEquals(0, hitBox.getX());
        assertEquals(0, hitBox.getY());
        assertEquals(BLOCK_SIZE, hitBox.getWidth());
        assertEquals(BLOCK_SIZE, hitBox.getHeight());
    }
}
