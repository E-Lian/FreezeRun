package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    public Game game;

    @BeforeEach
    public void setup() {
        game = new Game(480, 448);
    }

    @Test
    public void testConstructor() {
        assertEquals(480, game.getMaxX());
        assertEquals(448, game.getGround());
        assertEquals(1, game.getEnemies().size());
        assertTrue(game.getFireballs().isEmpty());
        assertFalse(game.isFrozen());
        assertFalse(game.isPaused());
        assertFalse(game.isEnded());
        assertFalse(game.getBlocks().isEmpty());
        assertEquals(3, game.getPlayerHp());
        assertTrue(game.getDoor() instanceof Door);
        assertFalse(game.isCanEnter());
        assertEquals(0, game.getItems().size());
    }

    @Test
    public void testToJson() {
        try {
            JSONObject jsonObject = game.toJson();
            assertEquals(480, jsonObject.getInt("maxX"));
            assertEquals(448, jsonObject.getInt("ground"));
            assertFalse(jsonObject.getBoolean("frozen"));
            assertTrue(jsonObject.getJSONObject("player") instanceof JSONObject);
            assertTrue(jsonObject.getJSONArray("enemies") instanceof JSONArray);
            assertTrue(jsonObject.getJSONArray("fireballs").isEmpty());
        } catch (Exception e) {
            fail("Shouldn't catch exception");
        }
    }

    @Test
    public void testToJsonEnded() {
        game.setEnded(true);
        try {
            JSONObject jsonObject = game.toJson();
            fail("Should've caught exception");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalStateException);
        }
    }

    @Test
    public void testToJsonFireball() {
        game.playerFire();
        try {
            JSONObject jsonObject = game.toJson();
            assertEquals(480, jsonObject.getInt("maxX"));
            assertEquals(448, jsonObject.getInt("ground"));
            assertFalse(jsonObject.getBoolean("frozen"));
            assertTrue(jsonObject.getJSONObject("player") instanceof JSONObject);
            assertTrue(jsonObject.getJSONArray("enemies") instanceof JSONArray);
            assertFalse(jsonObject.getJSONArray("fireballs").isEmpty());
        } catch (Exception e) {
            fail("Shouldn't catch exception");
        }
    }

    @Test
    public void testPlayerWalk() {
        game.playerWalk(true);
        assertEquals(4, game.getPlayer().getDx());
        game.playerWalk(false);
        assertEquals(-4, game.getPlayer().getDx());
    }

    @Test
    public void testPlayerJump() {
        game.playerJump();
        assertTrue(game.getPlayer().getDy() <= 0);
    }

    @Test
    public void testPlayerFire() {
        assertTrue(game.getFireballs().isEmpty());
        game.playerFire();
        assertEquals(1, game.getFireballs().size());
    }

    //@Test
    public void freezeTest() throws InterruptedException {
        assertFalse(game.isFrozen());
        game.freeze();
        assertTrue(game.isFrozen());
        Thread.sleep(3000L);
        game.tick();
        assertFalse(game.isFrozen());
        setup();
        game.freeze();
        Thread.sleep(4000L);
        game.freeze();
        game.tick();
        assertFalse(game.isFrozen());
        Thread.sleep(5000L);
        game.freeze();
        game.tick();
        assertTrue(game.isFrozen());
    }

    @Test
    public void isFrozenTest() {
        assertFalse(game.isFrozen());
        game.freeze();
        assertTrue(game.isFrozen());
    }

    @Test
    public void tickFireballTest() {
        game = new Game(52, 52);
        game.playerFire();
        game.tick();
        assertEquals(1, game.getFireballs().size());
        game.tick();
        assertFalse(game.getFireballs().isEmpty());
    }

    @Test
    public void tickFrozenTest() throws InterruptedException {
        game.setFrozen(false);
        game.freeze();
        game.tick();
        assertEquals(64, game.getPlayerX());
        assertEquals(448, game.getPlayerY());
        assertEquals(32, game.getEnemies().get(0).getCx());
        assertEquals(161, game.getEnemies().get(0).getCy());
        Thread.sleep(4000L);
        game.tick();
        assertFalse(game.isFrozen());
        game.tick();
        assertEquals(33, game.getEnemies().get(0).getCx());
        assertEquals(161, game.getEnemies().get(0).getCy());
    }

    @Test
    public void testTickGameOver() throws InterruptedException {
        Player player = game.getPlayer();
        Enemy enemy = game.getEnemies().get(0);
        player.setCx(enemy.getCx());
        player.setCy(enemy.getCy());
        game.tick();
        Thread.sleep(1000L);
        player.setCx(enemy.getCx());
        player.setCy(enemy.getCy());
        game.tick();
        Thread.sleep(1000L);
        player.setCx(enemy.getCx());
        player.setCy(enemy.getCy());
        game.tick();
        Thread.sleep(1000L);
        player.setCx(enemy.getCx());
        player.setCy(enemy.getCy());
        game.tick();
        assertEquals(0, game.getPlayerHp());
        game.tick();
        assertTrue(game.isEnded());
    }

    @Test
    public void testCheckCollisionsNoCollisions() {
        game.checkCollisions();
        testConstructor();
    }

    @Test
    public void testCheckCollisionEnemyHitPlayer() throws InterruptedException {
        Enemy e = game.getEnemies().get(0);
        Player p = game.getPlayer();
        p.setCx(e.getCx());
        p.setCy(e.getCy());
        game.checkCollisions();
        assertEquals(-20, p.getDx());
        assertEquals(2, game.getPlayerHp());
        p.setIsRight(false);
        p.update(1.0);
        Thread.sleep(1000L);
        p.setCx(e.getCx());
        p.setCy(e.getCy());
        game.checkCollisions();
        assertEquals(20, p.getDx());
        assertEquals(1, game.getPlayerHp());
    }

    @Test
    public void testPause() {
        assertFalse(game.isPaused());
        game.pause();
        assertTrue(game.isPaused());
        game.pause();
        assertFalse(game.isPaused());
    }

    @Test
    public void testEnterNextLevelGeneral() {
        Door door = game.getDoor();
        game.enterNextLevel();
        assertNotEquals(door, game.getDoor());
    }

    @Test
    public void testEnterNextLevelEnd() {
        game.enterNextLevel();
        assertFalse(game.isEnded());
        game.enterNextLevel();
        assertTrue(game.isEnded());
        assertEquals(0, game.getEnemies().size());
        assertEquals(0, game.getFireballs().size());
    }
}
