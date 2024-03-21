package ui;

import model.Block;
import model.Enemy;
import model.Fireball;
import model.Game;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static ui.GraphicsGame.*;

// the panel that renders the game
public class GamePanel extends JPanel {

    private Game game;
    private GraphicsGame gg;

    public GamePanel(Game g, GraphicsGame gg) {
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setBackground(Color.DARK_GRAY);
        this.game = g;
        this.gg = gg;
    }

    @Override
    protected void paintComponent(Graphics g) {
        // it is painting
        // this method isn't getting the right data
        super.paintComponent(g);
        drawGame(g);

        if (game.isEnded()) {
            gameOver(g);
        }
    }

    // Draws the game
    // modifies: g
    // effects:  draws the game onto g
    private void drawGame(Graphics g) {
        drawMap(g);
        drawPlayer(g);
        drawEnemies(g);
        drawFireballs(g);
    }

    // MODIFIES: g
    // EFFECTS: draws the map
    private void drawMap(Graphics g) {
        ArrayList<Block> map = this.game.getBlocks();
        for (Block b : map) {
            BufferedImage blockImg = b.getImg();
            g.drawImage(blockImg, b.getCx(), b.getCy(), BLOCK_SIZE, BLOCK_SIZE, null);
        }
    }

    // MODIFIES: g
    // EFFECTS: draws the player onto g
    private void drawPlayer(Graphics g) {
        BufferedImage playerImg = this.game.getPlayerImage();
        g.drawImage(playerImg, this.game.getPlayerX(), this.game.getPlayerY(), BLOCK_SIZE, BLOCK_SIZE, null);
    }

    // MODIFIES: g
    // EFFECTS: draws all enemies onto g
    private void drawEnemies(Graphics g) {
        for (Enemy next : this.game.getEnemies()) {
            drawEnemy(g, next);
        }
    }

    // MODIFIES: g
    // EFFECTS: draws the given enemy onto g
    private void drawEnemy(Graphics g, Enemy e) {
        BufferedImage enemyImg = e.getImg();
        g.drawImage(enemyImg, e.getCx(), e.getCy(), BLOCK_SIZE, BLOCK_SIZE, null);
    }

    // MODIFIES: g
    // EFFECTS: draws all fireballs onto g
    private void drawFireballs(Graphics g) {
        for (Fireball next : this.game.getFireballs()) {
            drawFireball(g, next);
        }
    }

    // MODIFIES: g
    // EFFECTS: draws the given fireball onto g
    private void drawFireball(Graphics g, Fireball f) {
        BufferedImage fireballImg = f.getImg();
        g.drawImage(fireballImg, f.getCx(), f.getCy(), BLOCK_SIZE, BLOCK_SIZE, null);
    }

    // Draws the "game over" message and replay instructions
    // modifies: g
    // effects:  draws "game over" and replay instructions onto g
    private void gameOver(Graphics g) {
        Color saved = g.getColor();
        g.setColor(new Color(0, 0, 0));
        g.setFont(new Font("Arial", 20, 20));
        FontMetrics fm = g.getFontMetrics();
//        centreString(OVER, g, fm, Game.HEIGHT / 2);
//        centreString(REPLAY, g, fm, Game.HEIGHT / 2 + 50);
        g.setColor(saved);
    }

    // Centres a string on the screen
    // modifies: g
    // effects:  centres the string str horizontally onto g at vertical position yPos
    private void centreString(String str, Graphics g, FontMetrics fm, int y) {
        int width = fm.stringWidth(str);
        g.drawString(str, (SCREEN_WIDTH - width) / 2, y);
    }

    public void saveGame(JsonWriter writer) {
        try {
            writer.open();
            writer.write(this.game);
            writer.close();
            System.out.println("Saved game");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write file");
        } catch (IllegalStateException ended) {
            System.out.println("Cannot save an ended game!");
        }
    }

    public void loadGame(JsonReader reader) {
        try {
            this.game = reader.read();
            this.gg.setGame(this.game);
            System.out.println("Loaded game");
        } catch (IOException e) {
            System.out.println("Unable to read");
        }
    }

    public Game getGame() {
        return this.game;
    }
}
