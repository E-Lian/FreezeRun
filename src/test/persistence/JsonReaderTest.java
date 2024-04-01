package persistence;

import model.Enemy;
import model.Game;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {

    @Test
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Game g = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testReaderGeneralGame() {
        try {
            JsonReader reader = new JsonReader("./data/testWriterGeneralGame.json");
            Game g = reader.read();
            assertEquals(480, g.getMaxX());
            assertEquals(448, g.getGround());
            assertFalse(g.isFrozen());
            assertEquals(2, g.getEnemies().size());
            assertEquals(64, g.getPlayerX());
            assertEquals(448, g.getPlayerY());
            assertTrue(g.getFireballs().isEmpty());
        } catch (Exception e) {
            fail("Shouldn't catch exception here");
        }
    }

    @Test
    public void testReaderFrozen() {
        try {
            JsonReader reader = new JsonReader("./data/testWriterFrozen.json");
            Game g = reader.read();

            assertEquals(2, g.getEnemies().size());
            assertEquals(480, g.getMaxX());
            assertEquals(448, g.getGround());
            assertTrue(g.isFrozen());
            assertEquals(64, g.getPlayerX());
            assertEquals(448, g.getPlayerY());
            assertTrue(g.getFireballs().isEmpty());

            Enemy enemy = g.getEnemies().get(1);
            assertEquals(32, enemy.getCx());
            assertEquals(161, enemy.getCy());
        } catch (Exception e) {
            fail("Shouldn't catch exception here");
        }
    }

    @Test
    public void testReaderNoEnemies() {
        try {
            JsonReader reader = new JsonReader("./data/testWriterNoEnemies.json");
            Game g = reader.read();
            g.load();

            assertEquals(0, g.getEnemies().size());
            assertEquals(480, g.getMaxX());
            assertEquals(448, g.getGround());
            assertFalse(g.isFrozen());
            assertEquals(64, g.getPlayerX());
            assertEquals(448, g.getPlayerY());
            assertTrue(g.getFireballs().isEmpty());
        } catch (Exception e) {
            fail("Shouldn't catch exception here");
        }
    }

    @Test
    public void testReaderFired() {
        try {
            JsonReader reader = new JsonReader("./data/testWriterFired.json");
            Game g = reader.read();

            assertEquals(2, g.getEnemies().size());
            assertEquals(480, g.getMaxX());
            assertEquals(448, g.getGround());
            assertFalse(g.isFrozen());
            assertEquals(64, g.getPlayerX());
            assertEquals(449, g.getPlayerY());
            assertFalse(g.getFireballs().isEmpty());
        } catch (Exception e) {
            fail("Shouldn't caught exception here");
        }
    }

}
