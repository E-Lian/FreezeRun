package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ScheduledExecutorService;

import static org.junit.jupiter.api.Assertions.*;

public class CharacterTest {
    public Character character;

    @BeforeEach
    public void setup() {
        character = new Player(0, 0);
    }

    @Test
    public void testToJson() {
        JSONObject jsonObject = character.toJson();
        assertEquals(0, jsonObject.getInt("cx"));
        assertEquals(0, jsonObject.getInt("cy"));
        assertEquals(0, jsonObject.getInt("dx"));
        assertEquals(0, jsonObject.getDouble("dy"));
        assertTrue(jsonObject.getBoolean("isRight"));
        assertEquals(3, jsonObject.getInt("hp"));
        assertFalse(jsonObject.getBoolean("jumping"));
        assertTrue(jsonObject.getBoolean("falling"));
    }

    @Test
    public void testUpateFalling() {
        assertFalse(character.isJumping());
        assertTrue(character.isFalling());
        character.update(1.0);
        assertEquals(0, character.getCy());
        assertEquals(1.0, character.getDy());
        assertEquals(0, character.getCx());
    }

    @Test
    public void testUpdateJumpingUp() {
        character.setFalling(false);
        character.setJumping(true);
        character.setDy(-10);
        character.update(1.0);
        assertFalse(character.isJumping());
        assertTrue(character.isFalling());
        assertEquals(-9, character.getDy());
    }

    @Test
    public void testUpdateJumpingDown() {
        character.setFalling(true);
        character.setJumping(true);
        character.setDy(10);
        character.update(1.0);
        assertEquals(10, character.getDy());
        assertEquals(0, character.getCy());
    }

    @Test
    public void testUpdateWalkingLeft() {
        character.setDx(-10);
        character.update(1.0);
        assertEquals(1.0, character.getDy());
        assertFalse(character.getIsRight());
        assertEquals(-10, character.getCx());
    }

    @Test
    public void testUpdateWalkingRight() {
        character.setIsRight(false);
        character.setDx(10);
        character.update(1.0);
        assertEquals(1.0, character.getDy());
        assertTrue(character.getIsRight());
        assertEquals(10, character.getCx());
    }

    @Test
    public void testUpdateWalkingJumping() {
        character.setDx(10);
        character.setDy(-20);
        character.update(1.0);
        assertEquals(-19, character.getDy());
        assertTrue(character.getIsRight());
        assertEquals(10, character.getCx());
        assertEquals(-20, character.getCy());
    }

    @Test
    public void testUpdateWalkingFalling() {
        character.setFalling(true);
        character.setDx(4);
        character.update(1.0);
        character.update(1.0);
        assertEquals(8, character.getCx());
        assertEquals(1, character.getCy());
        assertTrue(character.getIsRight());
        assertEquals(4, character.getDx());
    }

}
