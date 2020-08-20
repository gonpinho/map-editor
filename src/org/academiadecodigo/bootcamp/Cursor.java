package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;


public class Cursor implements KeyboardHandler {

    private Artboard artboard;
    private Keyboard keyboard;
    private Rectangle rectangle;
    private int col;
    private int row;
    private boolean isPainting;

    private KeyboardEvent[] keyboardInputs;
    private int[] keyboardInputsTypes = {
            KeyboardEvent.KEY_LEFT,
            KeyboardEvent.KEY_RIGHT,
            KeyboardEvent.KEY_UP,
            KeyboardEvent.KEY_DOWN,
            KeyboardEvent.KEY_SPACE,
            KeyboardEvent.KEY_C,
            KeyboardEvent.KEY_S,
            KeyboardEvent.KEY_L,
            KeyboardEvent.KEY_N
    };

    public Cursor (Artboard artboard) {
        this.artboard = artboard;
        this.keyboard = new Keyboard(this);
        this.col = 0;
        this.row = 0;

        rectangle = new Rectangle(Artboard.PADDING, Artboard.PADDING, Artboard.CELL_SIZE, Artboard.CELL_SIZE);
        rectangle.setColor(Color.BLACK);
        rectangle.fill();

        init();
    }

    public void init() {

        keyboardInputs = new KeyboardEvent[keyboardInputsTypes.length];

        for (int i = 0; i < keyboardInputsTypes.length; i++) {
            keyboardInputs[i] = new KeyboardEvent();
            keyboardInputs[i].setKey(keyboardInputsTypes[i]);
            keyboardInputs[i].setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            keyboard.addEventListener(keyboardInputs[i]);
        }

        KeyboardEvent spaceRelease = new KeyboardEvent();
        spaceRelease.setKey(KeyboardEvent.KEY_SPACE);
        spaceRelease.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        keyboard.addEventListener(spaceRelease);

    }


    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_LEFT:
                moveLeft();
                break;
            case KeyboardEvent.KEY_RIGHT:
                moveRight();
                break;
            case KeyboardEvent.KEY_UP:
                moveUp();
                break;
            case KeyboardEvent.KEY_DOWN:
                moveDown();
                break;
            case KeyboardEvent.KEY_SPACE:
                isPainting = true;
                tryToPaint();
                break;
            case KeyboardEvent.KEY_C:
                artboard.clear();
                break;
            case KeyboardEvent.KEY_S:
                artboard.save();
                break;
            case KeyboardEvent.KEY_L:
                artboard.load();
                break;
            case KeyboardEvent.KEY_N:
                Color newColor = Colors.getNextColor();
                rectangle.setColor(newColor);
                artboard.changeColor(newColor);
                break;
        }

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()){
            case KeyboardEvent.KEY_SPACE:
                isPainting = false;
                break;
        }
    }

    private void moveLeft() {
        if (getCol() == 0) {
            return;
        }
        col--;
        rectangle.translate(-Artboard.CELL_SIZE, 0);
        tryToPaint();
    }

    private void moveRight() {
        if (getCol() == (artboard.getCols() - 1)) {
            return;
        }
        col++;
        rectangle.translate(Artboard.CELL_SIZE, 0);
        tryToPaint();
    }

    private void moveUp() {
        if (getRow() == 0) {
            return;
        }
        row--;
        rectangle.translate(0, -Artboard.CELL_SIZE);
        tryToPaint();
    }

    private void moveDown() {
        if (getRow() == (artboard.getRows() - 1)) {
            return;
        }
        row++;
        rectangle.translate(0, Artboard.CELL_SIZE);
        tryToPaint();
    }


    private void tryToPaint() {
        if (isPainting) {
            artboard.paint();
            rectangle.delete();
            rectangle.fill();
        }
    }

    public void gfxReset() {
        rectangle.delete();
        rectangle.fill();
    }


    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }


}
