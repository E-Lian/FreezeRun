package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static ui.GraphicsGame.BLOCK_SIZE;

public class TrampolineTest {
    public Trampoline trampoline;

    @BeforeEach
    public void setup() {
        trampoline = new Trampoline(100, 100);
    }

    @Test
    public void testConstructor() {
        assertFalse(trampoline.isActivated());
        assertEquals(100, trampoline.getCx());
        assertEquals(100, trampoline.getCy());
        Rectangle hitBox = trampoline.getHitBox();
        assertEquals(BLOCK_SIZE, hitBox.getWidth());
        assertEquals(16, hitBox.getHeight());
    }

    @Test
    public void testHitBox() {
        trampoline.activate();
        Rectangle hitBox = trampoline.getHitBox();
        assertEquals(BLOCK_SIZE, hitBox.getWidth());
        assertEquals(BLOCK_SIZE, hitBox.getHeight());
    }
}
