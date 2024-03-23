package model;

import ui.GamePanel;
import ui.GraphicsGame;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import static model.Game.ENEMY_SPEED;

// represents a level in game
public class Level {
    private Game game;
    private int[][] map;

    public Level(Game game) {
        this.game = game;
        map = new int[GraphicsGame.row][GraphicsGame.col];
        loadMap();
    }

    // MODIFIES: this
    // EFFECTS: load level 1's map from its corresponding file
    public void loadMap() {
        try {
            File file = new File("./data/maps/level1.txt");
            Scanner reader = new Scanner(file);

            for (int i = 0; i < map.length; i++) {
                String line = reader.nextLine();
                for (int j = 0; j < map[i].length; j++) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[j]);
                    map[i][j] = num;
                }
            }

            reader.close();
        } catch (Exception e) {
            //
        }
    }

    // MODIFIES: game
    // EFFECTS: returns a list containing all blocks in this map
    public ArrayList<Block> realizeMap() {
        int x = 0;
        int y = 0;

        ArrayList<Block> blocks = new ArrayList<>();

        for (int[] ints : map) {
            for (int block : ints) {
                switch (block) {
                    case 1:
                        blocks.add(new Brick(x, y));
                        break;
                    case 9:
                        this.game.setPlayer(new Player(x, y));
                        break;
                    case 8:
                        this.game.addEnemy(new Enemy(x, y, ENEMY_SPEED, 0));
                }
                x += GraphicsGame.BLOCK_SIZE;
            }
            x = 0;
            y += GraphicsGame.BLOCK_SIZE;
        }

        return blocks;
    }

    public int[][] getMap() {
        return map;
    }
}
