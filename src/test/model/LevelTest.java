package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class LevelTest {
    public Level level;
    public Game g;

    @BeforeEach
    public void setup() {
        g = new Game(480, 448);
        try {
            level = new Level(g, 1);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void loadMapTest() {
        try {
            level.loadMap();
            int[][] map = level.getMap();
            for (int[] ints : map) {
                for (int i : ints) {
                    if (i != 0 && i != 1 && i != 8 && i != 9 && i != 2 && i != 3) {
                        fail("file loaded incorrectly");
                    }
                }
            }

        } catch (Exception e) {
            fail("Shouldn't have caught exception here");
        }
    }

    @Test
    public void testRealizeMap() {
        level.realizeMap();
        ArrayList<Block> blocks = g.getBlocks();
        assertEquals(82, blocks.size());
        Player testPlayer = g.getPlayer();
        assertEquals(64, testPlayer.getCx());
        assertEquals(448, testPlayer.getCy());
    }
}
