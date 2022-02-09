package dev.slohth.rubikscube.cube.move;

public enum MoveType {

    UP, DOWN, RIGHT, LEFT, FRONT, BACK;

    public boolean isOpposite(MoveType type) {
        switch (this) {
            case UP -> { return type == DOWN; }
            case DOWN -> { return type == UP; }
            case RIGHT -> { return type == LEFT; }
            case LEFT -> { return type == RIGHT; }
            case FRONT -> { return type == BACK; }
            case BACK -> { return type == FRONT; }
        }
        throw new EnumConstantNotPresentException(MoveType.class, "what");
    }

}
