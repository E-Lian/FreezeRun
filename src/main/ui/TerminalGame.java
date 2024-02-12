package ui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class TerminalGame {
    private Game game;
    private Screen screen;
    private WindowBasedTextGUI endGui;
    private TextGraphics text;

    public void start() throws IOException, InterruptedException {
        screen = new DefaultTerminalFactory().createScreen();
        screen.startScreen();

        TerminalSize terminalSize = screen.getTerminalSize();
        this.text = screen.newTextGraphics();

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
            if (!game.isPaused()) {
                tick();
                Thread.sleep(1000L / Game.TICKS_PER_SECOND);
            } else {
                onPause();
                Thread.sleep(1000L / Game.TICKS_PER_SECOND);
            }
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

    // EFFECTS: render game but doesn't update when on pause
    private void onPause() throws IOException {
        handleUserInput();

        screen.setCursorPosition(new TerminalPosition(0, 0));
        screen.clear();
        renderPause();
        screen.refresh();

        screen.setCursorPosition(new TerminalPosition(screen.getTerminalSize().getColumns() - 1, 0));
    }

    // MODIFIES: this
    // EFFECTS: watch and respond to user's inputs
    private void handleUserInput() throws IOException {
        KeyStroke stroke = screen.pollInput();

        if (stroke == null) {
            return;
        }

        if (Objects.requireNonNull(stroke.getKeyType()) == KeyType.Escape) {
            game.pause();
        }

        if (stroke.getCharacter() != null && !game.isPaused()) {
            char c = stroke.getCharacter();
            gameWalk(c);
            switch (c) {
                case 'w':
                    game.playerJump();
                    break;
                case 'f':
                    // TODO: implements freeze time
                    break;
                case ' ':
                    game.playerFire();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: control player to walk left or right based on input char
    private void gameWalk(char c) {
        if (c == 'a') {
            game.playerWalk('l');
        } else if (c == 'd') {
            game.playerWalk('r');
        }
    }

    // EFFECTS: draw pause screen
    private void renderPause() {
        drawPlayer();
        drawEnemies();
        text.setForegroundColor(TextColor.ANSI.CYAN);
        text.putString(20, 20, "PAUSED");
    }

    // EFFECTS: draw everything on screen
    private void render() {
        drawPlayer();
        drawEnemies();
        drawFireballs();
    }

    // EFFECTS: print the player coordinates
    private void drawPlayer() {
        text.setForegroundColor(TextColor.ANSI.YELLOW);
        text.putString(1, 0, "Player: ");

        text = screen.newTextGraphics();
        text.setForegroundColor(TextColor.ANSI.WHITE);
        text.putString(8, 0, game.getPlayerX() + "," + game.getPlayerY());
    }

    // EFFECTS: print the enemies' coordinates
    private void drawEnemies() {
        ArrayList<Enemy> enemies = game.getEnemies();
        if (enemies.isEmpty()) {
            text.setForegroundColor(TextColor.ANSI.RED);
            text.putString(1, 2, "No Enemies");
        }

        for (Enemy e : enemies) {
            text.setForegroundColor(TextColor.ANSI.RED);
            text.putString(1, 2, "Enemy: ");

            text = screen.newTextGraphics();
            text.setForegroundColor(TextColor.ANSI.WHITE);
            text.putString(8, 2, e.getCx() + "," + e.getCy());
        }

    }

    // EFFECTS: print the fireballs' coordinates
    private void drawFireballs() {
        ArrayList<Fireball> fireballs = game.getFireballs();
        if (!fireballs.isEmpty()) {
            for (Fireball f : fireballs) {
                text.setForegroundColor(TextColor.ANSI.BLUE);
                text.putString(1, 4 + fireballs.indexOf(f), "Fireball " + ":");

                text = screen.newTextGraphics();
                text.setForegroundColor(TextColor.ANSI.WHITE);
                text.putString(10, 4 + fireballs.indexOf(f), f.getCx() + "," + f.getCy());
            }
        }
    }

}
