package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import java.io.*;

public class Artboard {

    public static final int PADDING = 10;
    public static final int CELL_SIZE = 20;
    private final String FILE_PATH = "resources/save.txt";

    private int cols;
    private int rows;
    private int totalPixels;

    private Rectangle background;
    private Cell[] cells;
    private Cursor cursor;

    private Color currentColor = Color.BLACK;


    public Artboard(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
        this.totalPixels = cols*rows;

        init();
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    private void init() {
        background = new Rectangle(PADDING, PADDING, cols*CELL_SIZE, rows*CELL_SIZE);
        background.draw();

        cells = new Cell[totalPixels];
        populatePixels();

        cursor = new Cursor(this);
    }


    private void populatePixels() {

        int col = 0;
        int row = 0;

        for (int i = 0; i < totalPixels; i++) {
            if (i == 0) {
                cells[i] = new Cell(col, row, CELL_SIZE);
                col++;
                continue;
            }
            if (i % rows == 0) {
                row++;
                col = 0;
            }
            cells[i] = new Cell(col, row, CELL_SIZE);
            col++;

        }
    }


    public void paint() {
            for (int i = 0; i < cells.length; i++) {
                if (cursor.getRow() == cells[i].getRow() && cursor.getCol() == cells[i].getCol()) {
                    cells[i].changeState(currentColor);
                }
            }
    }

    public void clear() {
        for (int i = 0; i < cells.length; i++) {
            cells[i].reset();
        }
    }

    public void changeColor(Color color) {
        currentColor = color;
    }


    public void save() {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(FILE_PATH));

            for (int i = 0; i < cells.length; i++) {
                writer.write(cells[i].toString());
                if (i != cells.length-1) {
                    writer.write("\n");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void load() {

        BufferedReader bReader = null;

        try {
            bReader = new BufferedReader(new FileReader(FILE_PATH));

            for(int i = 0; i < cells.length; i++) {
                String line = bReader.readLine();
                String[] states = line.split(",");
                cells[i].setLoadingState(states[0], Colors.convertStringToColor(states[1]));
            }

            cursor.gfxReset();

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                bReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

