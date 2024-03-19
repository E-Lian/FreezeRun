package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import ui.GamePanel;

public class LevelTest {
    public Level level;
    public GamePanel gp;
    public Game g;

    @BeforeEach
    public void setup() {
        try {
            level = new Level();
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void loadMapTest() {
        try {
            level.loadMap();
            int[][] map = level.getMap();
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    if (i == 0 || i == map.length - 1 || j == 0 || j == map[i].length - 1) {
                        assertEquals(1, map[i][j]); // edges have to be 1
                    } else {
                        assertEquals(0, map[i][j]);
                    }
                }
            }

        } catch (Exception e) {
            fail("Shouldn't have caught exception here");
        }
    }

    // TODO: finish other tests
}
