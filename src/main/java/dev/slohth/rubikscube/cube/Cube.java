package dev.slohth.rubikscube.cube;

import dev.slohth.rubikscube.cubit.Cubit;
import dev.slohth.rubikscube.cubit.CubitRotation;

public class Cube {

    private final Cubit[] cubits;

    private int moves;

    public Cube() {
        this.cubits = new Cubit[27];
        for (int i = 0; i < this.cubits.length; i++) this.cubits[i] = new Cubit(this, i);
    }

    public boolean isSolved() {
        for (CubeFace face : CubeFace.values()) {
            for (int i = 0; i < 9; i++) {
                if (this.getCubits()[face.getCubits()[i]].getOrientation()[face.getId()] != face.getId()) return false;
            }
        }
        return true;
    }

    public void rotate(CubeRotation rotation) {
        moves++;
        byte[] indexes = rotation.getFace().getCubits();

        Cubit[] temp = this.cubits.clone();

        for (byte index : indexes) {
            for (CubitRotation r : rotation.getRotations()) this.cubits[index].rotate(r);
        }

        byte[] shifted = this.rotate(indexes);
        if (rotation.isPrime()) for (int i = 0; i < 2; i++) shifted = this.rotate(shifted);

        for (int i = 0; i < shifted.length; i++) this.cubits[indexes[i]] = temp[shifted[i]];
    }

    private byte[] rotate(byte[] arr) {
        return new byte[] { arr[6], arr[3], arr[0], arr[7], arr[4], arr[1], arr[8], arr[5], arr[2] };
    }

    public void display() {
        int count = 0;
        byte[] d = new byte[54];
        for (CubeFace face : CubeFace.values()) {
            for (byte index : face.getCubits()) {
                d[count] = this.cubits[index].getOrientation()[face.getId()];
                count++;
            }
        }

        System.out.println("      " + Colors.getColor(0) + " " + Colors.getColor(1) + " " + Colors.getColor(2));
        System.out.println("      " + Colors.getColor(3) + " " + Colors.getColor(4) + " " + Colors.getColor(5));
        System.out.println("      " + Colors.getColor(6) + " " + Colors.getColor(7) + " " + Colors.getColor(8));
        System.out.println(Colors.getColor(9) + " " + Colors.getColor(10) + " " + Colors.getColor(11) + " "
                + Colors.getColor(18) + " " + Colors.getColor(19) + " " + Colors.getColor(20) + " "
                + Colors.getColor(27) + " " + Colors.getColor(28) + " " + Colors.getColor(29) + " "
                + Colors.getColor(36) + " " + Colors.getColor(37) + " " + Colors.getColor(38));
        System.out.println(Colors.getColor(12) + " " + Colors.getColor(13) + " " + Colors.getColor(14) + " "
                + Colors.getColor(21) + " " + Colors.getColor(22) + " " + Colors.getColor(23) + " "
                + Colors.getColor(30) + " " + Colors.getColor(31) + " " + Colors.getColor(32) + " "
                + Colors.getColor(39) + " " + Colors.getColor(40) + " " + Colors.getColor(41));
        System.out.println(Colors.getColor(15) + " " + Colors.getColor(16) + " " + Colors.getColor(17) + " "
                + Colors.getColor(24) + " " + Colors.getColor(25) + " " + Colors.getColor(26) + " "
                + Colors.getColor(33) + " " + Colors.getColor(34) + " " + Colors.getColor(35) + " "
                + Colors.getColor(42) + " " + Colors.getColor(43) + " " + Colors.getColor(44));
        System.out.println("      " + Colors.getColor(45) + " " + Colors.getColor(46) + " " + Colors.getColor(47));
        System.out.println("      " + Colors.getColor(48) + " " + Colors.getColor(49) + " " + Colors.getColor(50));
        System.out.println("      " + Colors.getColor(51) + " " + Colors.getColor(52) + " " + Colors.getColor(53));

    }

    private enum Colors {
        RED("\033[0;31m", 0),
        BLUE("\033[0;34m", 1),
        GREEN("\033[0;32m", 2),
        YELLOW("\033[0;33m", 3),
        WHITE("\033[0;37m", 4),
        PURPLE("\033[0;35m", 5);

        Colors(String color, int i) {
            COLOR = color;
            NUM = i;
        }

        private String COLOR;
        private int NUM;

        private final String symbol = "■";

        private static String getColor(int i) {
            for (Colors value : values()) {
                if (value.NUM == i/9)
                    return value.COLOR + value.symbol;
            }
            return "F";
        }
    }

    public Cubit[] getCubits() { return this.cubits; }

    public int getMoves() { return this.moves; }
    public void resetMoves() { this.moves = 0; }
}
