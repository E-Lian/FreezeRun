package ui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// represents the main window which the game is drawn and played
// Reference: https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase/
public class GraphicsGame extends JFrame {
    // screen fields
    static final int ORIGINAL_BLOCK_SIZE = 16; // 16 pixel block
    static final int SCALE = 2;
    // size of each block = (originalBlockSize * scale)^2
    public static final int BLOCK_SIZE = ORIGINAL_BLOCK_SIZE * SCALE;
    public static final int row = 16;
    public static final int col = 16;

    public static final int SCREEN_WIDTH = BLOCK_SIZE * row;
    public static final int SCREEN_HEIGHT = BLOCK_SIZE * col;


    private static final int INTERVAL = 10;
    private Game game;
    private GamePanel gp;

    // Constructs main window
    // effects: sets up window in which Space Invaders game will be played
    public GraphicsGame() {
        super("Freeze Runner");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);
        game = new Game(SCREEN_WIDTH - BLOCK_SIZE, SCREEN_HEIGHT - 2 * BLOCK_SIZE);
        gp = new GamePanel(game);
        add(gp);
        addKeyListener(new KeyHandler());
        pack();
        centreOnScreen();
        setVisible(true);
        addTimer();
    }

    // Set up timer
    // modifies: none
    // effects:  initializes a timer that updates game each
    //           INTERVAL milliseconds
    private void addTimer() {
        Timer t = new Timer(INTERVAL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!game.isPaused()) {
                    game.tick();
                }
                gp.repaint();
            }
        });

        t.start();
    }

    // Centres frame on desktop
    // modifies: this
    // effects:  location of frame is set so frame is centred on desktop
    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }

    /*
     * A key handler to respond to key events
     */
    private class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (game.isPaused()) {
                game.keyPressedPaused(e.getKeyCode());
            } else {
                game.keyPressed(e.getKeyCode());
            }
        }
    }

}

