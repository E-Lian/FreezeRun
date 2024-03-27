package ui;

import model.Game;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

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


    private static final int INTERVAL = 25;
    private Game game;
    private GamePanel gp;
    private ArrayList<String> actionList;

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
        addKeyListener(new InputListener(this));
        actionList = new ArrayList<>();
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
                handleActions();
                if (!game.isPaused()) {
                    game.tick();
                }
                gp.repaint();
            }
        });

        t.start();
    }

    // MODIFIES: game
    // EFFECTS: execute all actions in the actionList
    private void handleActions() {
        for (String action : actionList) {
            if (action.equals("pause")) {
                game.pause();
                actionList.clear();
                return;
            } else if (action.equals("walkLeft")) {
                game.playerWalk(false);
            } else if (action.equals("walkRight")) {
                game.playerWalk(true);
            } else if (action.equals("jump")) {
                game.playerJump();
            } else if (action.equals("freeze")) {
                game.freeze();
            } else if (action.equals("fire")) {
                game.playerFire();
            } else if (action.equals("save")) {
                saveGame();
            } else if (action.equals("load")) {
                loadGame();
            } else if (action.equals("enter")) {
                enterNextLevel();
            }
        }
    }

    // MODIFIES: game
    // EFFECTS: increments game's level if player is at door
    public void enterNextLevel() {
        if (game.isCanEnter()) {
            game.enterNextLevel();
        }
    }

    // Centres frame on desktop
    // MODIFIES: this
    // EFFECTS: location of frame is set so frame is centred on desktop
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
            this.game.load();
            System.out.println("Loaded game");
        } catch (IOException e) {
            System.out.println("Unable to read");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds the given action to the actionList
    public void addToActionList(String action) {
        if (!actionList.contains(action)) {
            actionList.add(action);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes the given action from the actionList
    public void removeFromActionList(String action) {
        actionList.remove(action);
    }

    public Game getGame() {
        return game;
    }
}

