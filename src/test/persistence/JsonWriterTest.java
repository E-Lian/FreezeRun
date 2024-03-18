package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonWriterTest {
    @Test
    void testWriterInvalidFile() {
        try {
            Game g = new Game(100, 100);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterGeneralGame() {
        try {
            Game g = new Game(100, 100);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralGame.json");
            writer.open();
            writer.write(g);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralGame.json");
            g = reader.read();
            assertEquals(100, g.getWIDTH());
            assertEquals(100, g.getHEIGHT());
            assertFalse(g.isFrozen());
            assertEquals(2, g.getEnemies().size());
            assertEquals(50, g.getPlayerX());
            assertEquals(100, g.getPlayerY());
            assertTrue(g.getFireballs().isEmpty());
        } catch (Exception e) {
            fail("Should've catch exception here");
        }
    }

    @Test
    void testWriterNoEnemies() {
        try {
            Game g = new Game(100, 100);
            g.playerFire();
            g.tick();
            assertTrue(g.getEnemies().isEmpty());

            JsonWriter writer = new JsonWriter("./data/testWriterNoEnemies.json");
            writer.open();
            writer.write(g);
            writer.close();


            JsonReader reader = new JsonReader("./data/testWriterNoEnemies.json");
            g = reader.read();
            assertEquals(1, g.getEnemies().size());
            assertEquals(100, g.getWIDTH());
            assertEquals(100, g.getHEIGHT());
            assertFalse(g.isFrozen());
            assertEquals(50, g.getPlayerX());
            assertEquals(100, g.getPlayerY());
            assertFalse(g.getFireballs().isEmpty());
        } catch (Exception e) {
            fail("Shouldn't catch exception here");
        }
    }

    @Test
    void testWriterFrozen() {
        try {
            Game g = new Game(100, 100);
            g.freeze();
            g.tick();

            JsonWriter writer = new JsonWriter("./data/testWriterFrozen.json");
            writer.open();
            writer.write(g);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterFrozen.json");
            g = reader.read();
            assertEquals(2, g.getEnemies().size());
            assertEquals(100, g.getWIDTH());
            assertEquals(100, g.getHEIGHT());
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
}
