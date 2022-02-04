package dev.slohth.rubikscube.display;

import java.awt.*;

public enum DisplayColor {

    RED(new Color(240, 53, 53), 0),
    BLUE(new Color(53, 103, 240), 1),
    WHITE(new Color(245, 245, 245), 2),
    GREEN(new Color(53, 163, 31), 3),
    YELLOW(new Color(252, 214, 43), 4),
    ORANGE(new Color(255, 144, 25), 5);

    private final Color color;
    private final int data;

    DisplayColor(Color color, int data) {
        this.color = color;
        this.data = data;
    }

    public Color getColor() {
        return this.color;
    }
    public int getData() { return this.data; }

    public DisplayColor next() {
        return switch (this) {
            case RED -> BLUE;
            case BLUE -> WHITE;
            case WHITE -> GREEN;
            case GREEN -> YELLOW;
            case YELLOW -> ORANGE;
            case ORANGE -> RED;
        };
    }

}
