package model;

import ui.GamePanel;
import ui.GraphicsGame;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Level {
    private int[][] map;

    public Level() {
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

    // EFFECTS: make blocks based on the map
    public ArrayList<Block> realizeMap() {
        int x = 0;
        int y = 0;

        ArrayList<Block> blocks = new ArrayList<>();

        for (int[] ints : map) {
            for (int block : ints) {
                if (block == 1) {
                    blocks.add(new Brick(x, y));
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
