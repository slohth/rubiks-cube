package dev.slohth.rubikscube.cube;

public enum CubeFace {

    UP(new byte[] { 0, 1, 2, 3, 4, 5, 6, 7, 8 }, 0),
    LEFT(new byte[] { 0, 3, 6, 9, 12, 15, 18, 21, 24 }, 1),
    FRONT(new byte[] { 6, 7, 8, 15, 16, 17, 24, 25, 26 }, 2),
    RIGHT(new byte[] { 8, 5, 2, 17, 14, 11, 26, 23, 20 }, 3),
    BACK(new byte[] { 2, 1, 0, 11, 10, 9, 20, 19, 18 }, 4),
    DOWN(new byte[] { 24, 25, 26, 21, 22, 23, 18, 19, 20 }, 5);

    private final byte[] cubits;
    private final byte id;

    CubeFace(byte[] cubits, int id) { this.cubits = cubits; this.id = (byte) id; }

    public byte[] getCubits() { return this.cubits; }

    public byte getId() { return this.id; }
}
