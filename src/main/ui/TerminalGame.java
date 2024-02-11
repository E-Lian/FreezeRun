package ui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import model.*;

import java.io.IOException;

public class TerminalGame {
    private Game game;
    private Screen screen;
    private WindowBasedTextGUI endGui;

    public void start() throws IOException, InterruptedException {
        screen = new DefaultTerminalFactory().createScreen();
        screen.startScreen();

        TerminalSize terminalSize = screen.getTerminalSize();

        game = new Game(
                // divide the columns in two
                // this is so we can make the each part of
                // the snake wide, since terminal characters are
                // taller than they are wide
                (terminalSize.getColumns() - 1) / 2,
                // first row is reserved for us
                terminalSize.getRows() - 2);

        beginTicks();
    }

    // EFFECTS:Begins the game cycle. Ticks once every Game.TICKS_PER_SECOND until
    // game has ended and the endGui has been exited.
    private void beginTicks() throws IOException, InterruptedException {
        while (!game.isEnded() || endGui.getActiveWindow() != null) {
            tick();
            Thread.sleep(1000L / Game.TICKS_PER_SECOND);
        }

        System.exit(0);
    }

    // EFFECTS: Handles one cycle in the game by taking user input,
    // ticking the game internally, and rendering the effects
    private void tick() throws IOException {
        handleUserInput();

        game.tick();

        screen.setCursorPosition(new TerminalPosition(0, 0));
        screen.clear();
        render();
        screen.refresh();

        screen.setCursorPosition(new TerminalPosition(screen.getTerminalSize().getColumns() - 1, 0));
    }

    // MODIFIES: this
    // EFFECTS: watch and respond to user's inputs
    private void handleUserInput() throws IOException {
        // TODO: implement pause and resume
        KeyStroke stroke = screen.pollInput();

        if (stroke == null) {
            return;
        }

        if (stroke.getCharacter() != null) {
            return;
        }

        switch (stroke.getKeyType()) {
            case ArrowUp:
                game.playerJump();
            case ArrowRight:
                game.playerWalk("right");
                break;
            case ArrowLeft:
                game.playerWalk("left");
                break;
        }
    }

    // EFFECTS: draw everything on screen
    private void render() {
        // TODO: draw enemies
        drawPlayer();
        //drawEnemies();
    }

    // EFFECTS: print the player coordinates
    private void drawPlayer() {
        TextGraphics text = screen.newTextGraphics();
        text.setForegroundColor(TextColor.ANSI.YELLOW);
        text.putString(1, 0, "Player: ");

        text = screen.newTextGraphics();
        text.setForegroundColor(TextColor.ANSI.WHITE);
        text.putString(8, 0, String.valueOf(game.getPlayerX()) + "," + String.valueOf(game.getPlayerY()));
    }

    // EFFECTS: print the enemies' coordinates
    private void drawEnemies() {
        // TODO: get enemies from Game and draw all enemies
    }

}
