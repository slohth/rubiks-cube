package dev.slohth.rubikscube.cubit;

import dev.slohth.rubikscube.cube.Cube;

public class Cubit {

    private static final byte[][] ORIENTATIONS = new byte[][] {
            new byte[] {0, 1, 2, 3, 4, 5},
            new byte[] {2, 1, 5, 3, 0, 4},
            new byte[] {5, 1, 4, 3, 2, 0},
            new byte[] {2, 0, 1, 5, 3, 4},
            new byte[] {3, 0, 2, 5, 4, 1},
            new byte[] {2, 3, 0, 1, 5, 4},
            new byte[] {5, 3, 2, 1, 4, 0},
            new byte[] {4, 3, 5, 1, 0, 2},
            new byte[] {0, 3, 4, 1, 2, 5},
            new byte[] {4, 5, 1, 0, 3, 2},
            new byte[] {1, 5, 2, 0, 4, 3},
            new byte[] {2, 5, 3, 0, 1, 4},
            new byte[] {0, 2, 3, 4, 1, 5},
            new byte[] {3, 2, 5, 4, 0, 1},
            new byte[] {4, 1, 0, 3, 5, 2},
            new byte[] {5, 4, 3, 2, 1, 0},
            new byte[] {1, 4, 5, 2, 0, 3},
            new byte[] {0, 4, 1, 2, 3, 5},
            new byte[] {3, 4, 0, 2, 5, 1},
            new byte[] {3, 5, 4, 0, 2, 1},
            new byte[] {4, 0, 3, 5, 1, 2},
            new byte[] {1, 0, 4, 5, 2, 3},
            new byte[] {5, 2, 1, 4, 3, 0},
            new byte[] {1, 2, 0, 4, 5, 3}
    };

    private final int id;
    private final Cube cube;

    private byte[] orientation;

    public Cubit(Cube cube, int id) {
        this.cube = cube; this.id = id;
        this.orientation = new byte[] { 0, 1, 2, 3, 4, 5 };
    }

    public void giveInput(byte[] input) {
        for (byte[] arr : ORIENTATIONS) {

            boolean match = true;
            for (int i = 0; i < arr.length; i++) {
                if (input[i] == -1) continue;
                if (arr[i] != input[i]) {
                    match = false; break;
                }
            }

            if (match) this.orientation = input.clone();
        }
    }

    public void rotate(CubitRotation rotation) {
        byte[] v = new byte[] { orientation[0], orientation[2], orientation[5], orientation[4] };
        byte[] h = new byte[] { orientation[1], orientation[2], orientation[3], orientation[4] };
        byte[] b;

        switch (rotation) {
            case UP -> {
                b = this.lShift(v);
                this.orientation[0] = b[0]; this.orientation[2] = b[1]; this.orientation[5] = b[2]; this.orientation[4] = b[3];
            }
            case DOWN -> {
                b = this.rShift(v);
                this.orientation[0] = b[0]; this.orientation[2] = b[1]; this.orientation[5] = b[2]; this.orientation[4] = b[3];
            }
            case LEFT -> {
                b = this.lShift(h);
                System.arraycopy(b, 0, this.orientation, 1, b.length);
            }
            case RIGHT -> {
                b = this.rShift(h);
                System.arraycopy(b, 0, this.orientation, 1, b.length);
            }
        }
    }

    private byte[] rShift(byte[] b) {
        byte temp = b[b.length - 1];
        System.arraycopy(b, 0, b, 1, b.length - 1);
        b[0] = temp;
        return b;
    }

    private byte[] lShift(byte[] b) {
        byte temp = b[0];
        System.arraycopy(b, 1, b, 0, b.length - 1);
        b[b.length - 1] = temp;
        return b;
    }

    public int getIndexInCube() {
        for (int i = 0; i < cube.getCubits().length; i++) if (this.id == cube.getCubits()[i].getId()) return i;
        return -1;
    }

    public byte[] getOrientation() { return this.orientation; }

    public int getId() { return this.id; }

}
