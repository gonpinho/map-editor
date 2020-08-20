package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.graphics.Color;


public class Colors {

    public static Color[] color = {
            Color.BLACK,
            Color.YELLOW,
            Color.GREEN,
            Color.BLUE,
            Color.CYAN,
            Color.DARK_GRAY,
            Color.GRAY,
            Color.LIGHT_GRAY,
            Color.MAGENTA,
            Color.ORANGE,
            Color.PINK,
            Color.RED
    };

    public static String[] colorNames = {
            "black",
            "yellow",
            "green",
            "blue",
            "cyan",
            "darkgray",
            "gray",
            "lightgray",
            "magenta",
            "orange",
            "pink",
            "red"
    };

    public static int currentIndex = 1;

    public static Color getNextColor() {
        if (currentIndex == color.length) {
            currentIndex = 0;
        }
        Color newColor = color[currentIndex];
        currentIndex++;
        return newColor;
    }

    public static String convertColorToString(Color c) {
        String colorName = null;
        for (int i = 0; i <  color.length; i++) {
            if (c.equals(color[i])) {
                colorName = colorNames[i];
            }
        }
        return colorName;
    }

    public static Color convertStringToColor(String colorRef) {
        Color newColor = null;
        for (int i = 0; i < colorNames.length; i++) {
            if (colorRef.equals(colorNames[i])) {
                newColor = color[i];
            }
        }
        return newColor;
    }


}
