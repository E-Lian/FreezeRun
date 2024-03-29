package model;

import ui.GraphicsGame;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import static model.Game.ENEMY_SPEED;

// represents a level in game
public class Level {
    private Game game;
    private int[][] map;
    private int levelNum;

    public Level(Game game, int levelNum) {
        this.game = game;
        this.levelNum = levelNum;
        map = new int[GraphicsGame.row][GraphicsGame.col];
        loadMap();
    }

    // MODIFIES: this
    // EFFECTS: load level 1's map from its corresponding file
    public void loadMap() {
        try {
            File file = new File("./data/maps/level" + levelNum + ".txt");
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
    public void realizeMap() {
        int x = 0;
        int y = 0;

        ArrayList<Block> blocks = new ArrayList<>();
        ArrayList<Item> items = new ArrayList<>();

        for (int[] ints : map) {
            for (int block : ints) {
                if (block == 1) {
                    blocks.add(new Brick(x, y));
                } else if (block == 2) {
                    game.setDoor(new Door(x, y));
                } else if (block == 3) {
                    items.add(new Trampoline(x, y));
                } else if (block == 8) {
                    game.addEnemy(new Enemy(x, y, ENEMY_SPEED, 0));
                } else if (block == 9) {
                    game.setPlayer(new Player(x, y));
                }
                x += GraphicsGame.BLOCK_SIZE;
            }
            x = 0;
            y += GraphicsGame.BLOCK_SIZE;
        }

        game.setMap(blocks, items);
    }

    public int[][] getMap() {
        return map;
    }
}
