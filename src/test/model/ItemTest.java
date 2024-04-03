package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ItemTest {
    public Item item;

    @BeforeEach
    public void setup() {
        item = new Trampoline(100, 100);
    }

    @Test
    public void testActivate() {
        assertFalse(item.isActivated());
        item.activate();
        assertTrue(item.isActivated());
    }

    @Test
    public void testUpdate() throws InterruptedException {
        assertFalse(item.isActivated());
        item.activate();
        assertTrue(item.isActivated());
        item.update();
        assertTrue(item.isActivated());
        Thread.sleep(500L);
        item.update();
        assertFalse(item.isActivated());
    }
}
