package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

// represents the main window which the game is drawn and played
// Reference: https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase/
public class GraphicsGame extends JFrame {
    // screen fields
    static final int ORIGINAL_BLOCK_SIZE = 16; // 16 pixel block
    public static final int SCALE = 2;
    // size of each block = (originalBlockSize * scale)^2
    public static final int BLOCK_SIZE = ORIGINAL_BLOCK_SIZE * SCALE;
    public static final int row = 16;
    public static final int col = 16;

    public static final int SCREEN_WIDTH = BLOCK_SIZE * row;
    public static final int SCREEN_HEIGHT = BLOCK_SIZE * col;


    private static final int INTERVAL = 10;
    private Game game;
    private GamePanel gp;

    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private static final String JSON_STORE = "./data/game.json";

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
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
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
    // effects: location of frame is set so frame is centred on desktop
    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }

    public void setGame(Game g) {
        this.game = g;
    }

    // EFFECTS: reads the current game to a file
    public void saveGame() {
        try {
            jsonWriter.open();
            jsonWriter.write(this.game);
            jsonWriter.close();
            System.out.println("Saved game");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write file");
        } catch (IllegalStateException ended) {
            System.out.println("Cannot save an ended game!");
        }
    }

    // MODIFIES: game, gp
    // EFFECTS: load game from file
    public void loadGame() {
        try {
            this.setGame(jsonReader.read());
            this.gp.setGame(this.game);
            System.out.println("Loaded game");
        } catch (IOException e) {
            System.out.println("Unable to read");
        }
    }

    /*
     * A key handler to respond to key events
     */
    private class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (game.isPaused()) {
                handleUserInputPaused(e.getKeyCode());
            } else {
                handleUserInput(e.getKeyCode());
            }
            // keyPressed is still being called and updates game normally after loading
        }

        // MODIFIES: game
        // EFFECTS: handles user input
        public void handleUserInput(int keyCode) {
            if (keyCode == 27) {
                game.pause();
                return;
            }
            if (keyCode == 65 || keyCode == 68) {
                game.playerWalk(keyCode == 68);
            }
            if (keyCode == 87) {
                game.playerJump();
            }
            if (keyCode == 70) {
                game.freeze();
            }
            if (keyCode == 32) {
                game.playerFire();
            }
        }


        // MODIFIES: game
        // EFFECTS: same as keyPressed, but behaves slightly differently since game is on pause
        public void handleUserInputPaused(int keyCode) {
            if (keyCode == 27) {
                game.pause();
            }
            if (keyCode == 10) {
                saveGame();
            }
            if (keyCode == 32) {
                loadGame();
            }
        }

    }
}

