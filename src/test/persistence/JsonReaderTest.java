package persistence;

import model.Enemy;
import model.Game;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Reader;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Game g = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderGeneralGame() {
        try {
            JsonReader reader = new JsonReader("./data/testWriterGeneralGame.json");
            Game g = reader.read();
            assertEquals(100, g.getMaxX());
            assertEquals(100, g.getMaxY());
            assertFalse(g.isFrozen());
            assertEquals(2, g.getEnemies().size());
            assertEquals(50, g.getPlayerX());
            assertEquals(100, g.getPlayerY());
            assertTrue(g.getFireballs().isEmpty());
        } catch (Exception e) {
            fail("Shouldn't catch exception here");
        }
    }

    @Test
    void testReaderFrozen() {
        try {
            JsonReader reader = new JsonReader("./data/testWriterFrozen.json");
            Game g = reader.read();

            assertEquals(2, g.getEnemies().size());
            assertEquals(100, g.getMaxX());
            assertEquals(100, g.getMaxY());
            assertTrue(g.isFrozen());
            assertEquals(50, g.getPlayerX());
            assertEquals(100, g.getPlayerY());
            assertTrue(g.getFireballs().isEmpty());

            Enemy enemy = g.getEnemies().get(0);
            assertEquals(50, enemy.getCx());
            assertEquals(22, enemy.getCy());
        } catch (Exception e) {
            fail("Shouldn't catch exception here");
        }
    }

    @Test
    void testReaderNoEnemies() {
        try {
            JsonReader reader = new JsonReader("./data/testWriterNoEnemies.json");
            Game g = reader.read();

            assertEquals(1, g.getEnemies().size());
            assertEquals(100, g.getMaxX());
            assertEquals(100, g.getMaxY());
            assertFalse(g.isFrozen());
            assertEquals(50, g.getPlayerX());
            assertEquals(100, g.getPlayerY());
            assertFalse(g.getFireballs().isEmpty());
        } catch (Exception e) {
            fail("Shouldn't catch exception here");
        }
    }
}
