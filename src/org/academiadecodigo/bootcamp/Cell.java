package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Cell {

    private boolean isFilled;
    private int col;
    private int row;
    private Rectangle rectangle;

    public Cell(int col, int row, int cellSize) {
        this.col = col;
        this.row = row;
        this.isFilled = false;

        rectangle = new Rectangle(col*cellSize + Artboard.PADDING, row*cellSize + Artboard.PADDING, cellSize, cellSize);
        rectangle.draw();

    }

    public boolean isFilled() {
        return isFilled;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public void changeState(Color color) {
        if (!rectangle.isFilled()) {
            isFilled = true;
            rectangle.setColor(color);
            rectangle.fill();
            return;
        }
        if (rectangle.getColor() != color) {
            rectangle.setColor(color);
            rectangle.fill();
            return;
        }
        isFilled = false;
        gfxReset();
    }

    public void reset() {
        isFilled = false;
        gfxReset();
    }

    public void gfxReset() {
        rectangle.delete();
        rectangle.setColor(Color.BLACK);
        rectangle.draw();
    }

    public String getStateString() {
        return isFilled ? "painted" : "not";
    }


    public String getColorToSave() {
        return Colors.convertColorToString(rectangle.getColor());
    }


    public void setLoadingState(String state, Color color) {
        if (state.equals("painted")) {
            rectangle.setColor(color);
            rectangle.fill();
            isFilled = true;
        }
    }

    @Override
    public String toString() {
        return getStateString() + "," + getColorToSave();
    }
}
