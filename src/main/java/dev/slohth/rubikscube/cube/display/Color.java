package dev.slohth.rubikscube.cube.display;

public enum Color {
    RED("\033[0;31m", 0),
    BLUE("\033[0;34m", 1),
    GREEN("\033[0;32m", 2),
    YELLOW("\033[0;33m", 3),
    WHITE("\033[0;37m", 4),
    PURPLE("\033[0;35m", 5);

    Color(String color, int i) {
        COLOR = color;
        NUM = i;
    }

    private String COLOR;
    private int NUM;

    private final String symbol = "■";

    public static String getColor(int i) {
        for (Color value : values()) {
            if (value.NUM == i)
                return value.COLOR + value.symbol;
        }
        return "F";
    }
}