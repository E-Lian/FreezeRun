package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class FireballTest {
    public Fireball fireball;

    @BeforeEach
    public void setup() {
        fireball = new Fireball(100, 100, true);
    }

    @Test
    public void testToJson() {
        JSONObject jsonObject = fireball.toJson();
        assertEquals(100, jsonObject.getInt("cx"));
        assertEquals(100, jsonObject.getInt("cy"));
        assertTrue(jsonObject.getBoolean("isRight"));
    }

    @Test
    public void testUpdateRight() {
        fireball.update();
        assertEquals(102, fireball.getCx());
        assertEquals(100, fireball.getCy());
        assertTrue(fireball.getIsRight());
    }

    @Test
    public void testUpdateLeft() {
        fireball = new Fireball(100, 100, false);
        fireball.update();
        assertEquals(98, fireball.getCx());
        assertEquals(100, fireball.getCy());
        assertFalse(fireball.getIsRight());
    }

    @Test
    public void testHitBox() {
        Rectangle hitBox = fireball.getHitBox();
        assertEquals(108, hitBox.getX());
        assertEquals(108, hitBox.getY());
        assertEquals(22, hitBox.getWidth());
        assertEquals(14, hitBox.getHeight());
    }
}
