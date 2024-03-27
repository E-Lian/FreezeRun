package ui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static ui.GraphicsGame.*;

// the panel that renders the game
public class GamePanel extends JPanel {

    private Game game;

    private final String saveString = "When pausing, press ENTER to save game";
    private final String loadString = "When pausing, press SPACE to load game";

    private final String moveInstruction = "Use W, A, D to move";
    private final String fireInstruction = "Press SPACE to fire at enemy";
    private final String freezeInstruction = "Press F to freeze time for 3 seconds";

    public GamePanel(Game g) {
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setBackground(Color.LIGHT_GRAY);
        this.game = g;
    }

    @Override
    protected void paintComponent(Graphics g) {
        // it is painting
        // this method isn't getting the right data
        super.paintComponent(g);

        if (game.isEnded()) {
            gameOver(g);
            return;
        }

        if (game.isPaused()) {
            drawGame(g);
            drawPauseScreen(g);
        } else if (game.isFrozen()) {
            drawFrozenGame(g);
        } else {
            drawGame(g);
        }
    }

    // MODIFIES: g
    // EFFECTS: draws the frozen game
    private void drawFrozenGame(Graphics g) {
        drawMap(g);
        drawEnemies(g);
        drawFireballs(g);
        g.setColor(new Color(0, 120, 255, 40));
        g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        drawPlayer(g);
    }

    // MODIFIES: g
    // EFFECTS: draws the pause menu
    private void drawPauseScreen(Graphics g) {
        g.setColor(new Color(100, 100, 100, 170));
        g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        g.setFont(new Font(Font.DIALOG, Font.CENTER_BASELINE, 20));
        FontMetrics fm = g.getFontMetrics();
        g.setColor(new Color(255, 80, 0));
        centreString("PAUSED", g, fm, 170);
        g.setColor(new Color(255, 255, 255));
        centreString(saveString, g, fm, 200);
        centreString(loadString, g, fm, 230);
        fm = g.getFontMetrics();
        g.setFont(new Font(Font.DIALOG, Font.CENTER_BASELINE, 20));
        centreString(moveInstruction, g, fm, 300);
        centreString(fireInstruction, g, fm, 330);
        centreString(freezeInstruction, g, fm, 360);

    }

    // Draws the game
    // MODIFIES: g
    // EFFECTS:  draws the game onto g
    private void drawGame(Graphics g) {
        drawMap(g);
        drawEnemies(g);
        drawFireballs(g);
        drawPlayer(g);
    }

    // MODIFIES: g
    // EFFECTS: draws the map
    private void drawMap(Graphics g) {
        ArrayList<Block> blocks = this.game.getBlocks();
        for (Block b : blocks) {
            BufferedImage blockImg = b.getImg();
            g.drawImage(blockImg, b.getCx(), b.getCy(), BLOCK_SIZE, BLOCK_SIZE, null);
        }

        Door door = game.getDoor();
        BufferedImage doorImg = door.getImg();
        g.drawImage(doorImg, door.getCx(), door.getCy(), BLOCK_SIZE, BLOCK_SIZE, null);
    }

    // MODIFIES: g
    // EFFECTS: draws the player onto g
    private void drawPlayer(Graphics g) {
        BufferedImage playerImg = this.game.getPlayer().getImg();
        g.drawImage(playerImg, this.game.getPlayerX(), this.game.getPlayerY(), BLOCK_SIZE, BLOCK_SIZE, null);
        g.setColor(new Color(200, 50, 0));
        g.setFont(new Font(Font.DIALOG, Font.CENTER_BASELINE, 20));
        g.drawString("HP: " + game.getPlayerHp(), BLOCK_SIZE + 10, BLOCK_SIZE + 25);
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

    // modifies: g
    // effects:  draws "game over" onto g
    private void gameOver(Graphics g) {
        Color saved = g.getColor();
        g.setColor(new Color(0, 0, 0));
        g.setFont(new Font(Font.DIALOG, Font.CENTER_BASELINE, 20));
        FontMetrics fm = g.getFontMetrics();
        centreString("GAME OVER", g, fm, game.getGround() / 2);
    }

    // Centres a string on the screen
    // modifies: g
    // effects:  centres the string str horizontally onto g at vertical position yPos
    private void centreString(String str, Graphics g, FontMetrics fm, int y) {
        int width = fm.stringWidth(str);
        g.drawString(str, (SCREEN_WIDTH - width) / 2, y);
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
