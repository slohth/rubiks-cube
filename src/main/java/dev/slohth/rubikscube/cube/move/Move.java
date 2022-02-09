package dev.slohth.rubikscube.cube.move;

import dev.slohth.rubikscube.cube.CubeRotation;

import java.util.InputMismatchException;

public class Move {

    private final MoveType type;
    private final int count;

    public Move(MoveType rotation, int count) {
        this.type = rotation;
        this.count = count;
    }

    public Move(CubeRotation rotation) {
        this.type = MoveType.valueOf(rotation.toString().split("_")[0]);
        this.count = rotation.isPrime() ? -1 : 1;
    }

    public MoveType getType() { return type; }
    public int getCount() { return count; }

    public boolean compare(Move move) {
        return type == move.type;
    }

    @Override
    public String toString() {
        return (String.valueOf(type.toString().charAt(0)) + count).replace("-", "'");
    }

    public static Move add(Move moveA, Move moveB) {
        if (moveA.compare(moveB)) return new Move(moveA.type, (moveA.count + moveB.count) % 4 == 3 ? -1 : (moveA.count + moveB.count) % 4);
        throw new InputMismatchException("loser");
    }

}
