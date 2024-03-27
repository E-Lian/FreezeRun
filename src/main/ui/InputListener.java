package ui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputListener extends KeyAdapter {

    private GraphicsGame graphicsGame;

    // EFFECTS: instantiate an InputListener to watch key events
    public InputListener(GraphicsGame graphicsGame) {
        this.graphicsGame = graphicsGame;
    }

    // MODIFIES: graphicsGame
    // EFFECTS: adds the pressed key's corresponding action to the graphicsGame based on isPause
    @Override
    public void keyPressed(KeyEvent e) {
        if (graphicsGame.getGame().isPaused()) {
            handleUserInputPausedPressed(e.getKeyCode());
        } else {
            handleUserInputPressed(e.getKeyCode());
        }
    }

    // MODIFIES: graphicsGame
    // EFFECTS: adds the pressed key's corresponding action to the graphicsGame when game is running
    private void handleUserInputPressed(int keyCode) {
        if (keyCode == 27) {
            addAction("pause");
        } else if (keyCode == 65) {
            addAction("walkLeft");
        } else if (keyCode == 68) {
            addAction("walkRight");
        } else if (keyCode == 87) {
            addAction("jump");
        } else if (keyCode == 70) {
            addAction("freeze");
        } else if (keyCode == 32) {
            addAction("fire");
        } else if (keyCode == 10) {
            addAction("enter");
        }
    }

    // MODIFIES: graphicsGame
    // EFFECTS: adds the pressed key's corresponding action to the graphicsGame when game is paused
    private void handleUserInputPausedPressed(int keyCode) {
        if (keyCode == 27) {
            addAction("pause");
        }
        if (keyCode == 10) {
            addAction("save");
        }
        if (keyCode == 32) {
            addAction("load");
        }
    }

    // MODIFIES: graphicsGame
    // EFFECTS: removes the pressed key's corresponding action to the graphicsGame
    @Override
    public void keyReleased(KeyEvent e) {
        if (graphicsGame.getGame().isPaused()) {
            handleUserInputPausedReleased(e.getKeyCode());
        } else {
            handleUserInputReleased(e.getKeyCode());
        }
    }

    // MODIFIES: graphicsGame
    // EFFECTS: removes the pressed key's corresponding action to the graphicsGame when game is running
    private void handleUserInputReleased(int keyCode) {
        if (keyCode == 27) {
            removeAction("pause");
        } else if (keyCode == 65) {
            removeAction("walkLeft");
        } else if (keyCode == 68) {
            removeAction("walkRight");
        } else if (keyCode == 87) {
            removeAction("jump");
        } else if (keyCode == 70) {
            removeAction("freeze");
        } else if (keyCode == 32) {
            removeAction("fire");
        } else if (keyCode == 10) {
            removeAction("enter");
        }
    }

    // MODIFIES: graphicsGame
    // EFFECTS: removes the pressed key's corresponding action to the graphicsGame when game is paused
    private void handleUserInputPausedReleased(int keyCode) {
        if (keyCode == 27) {
            removeAction("pause");
        }
        if (keyCode == 10) {
            removeAction("save");
        }
        if (keyCode == 32) {
            removeAction("load");
        }
    }


    // MODIFIES: graphicsGame
    // EFFECTS: adds given action to graphicsGame
    public void addAction(String action) {
        graphicsGame.addToActionList(action);
    }

    // MODIFIES: graphicsGame
    // EFFECTS: removes given action to graphicsGame
    public void removeAction(String action) {
        graphicsGame.removeFromActionList(action);
    }
}
