package dev.slohth.rubikscube.cube;

import dev.slohth.rubikscube.cube.display.Color;
import dev.slohth.rubikscube.cubit.Cubit;
import dev.slohth.rubikscube.cubit.CubitRotation;

import java.util.ArrayList;
import java.util.List;

public class Cube {

    private final Cubit[] cubits;
    private int moves;
    private List<CubeRotation> rotations = new ArrayList<>();

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
        rotations.add(rotation);
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

        StringBuilder display = new StringBuilder();

        display.append("      ").append(Color.getColor(d[0])).append(" ").append(Color.getColor(d[1])).append(" ").append(Color.getColor(d[2])).append("\n");
        display.append("      ").append(Color.getColor(d[3])).append(" ").append(Color.getColor(d[4])).append(" ").append(Color.getColor(d[5])).append("\n");
        display.append("      ").append(Color.getColor(d[6])).append(" ").append(Color.getColor(d[7])).append(" ").append(Color.getColor(d[8])).append("\n");
        display.append(Color.getColor(d[9])).append(" ").append(Color.getColor(d[10])).append(" ").append(Color.getColor(d[11])).append(" ").append(Color.getColor(d[18])).append(" ").append(Color.getColor(d[19])).append(" ").append(Color.getColor(d[20])).append(" ").append(Color.getColor(d[27])).append(" ").append(Color.getColor(d[28])).append(" ").append(Color.getColor(d[29])).append(" ").append(Color.getColor(d[36])).append(" ").append(Color.getColor(d[37])).append(" ").append(Color.getColor(d[38])).append("\n");
        display.append(Color.getColor(d[12])).append(" ").append(Color.getColor(d[13])).append(" ").append(Color.getColor(d[14])).append(" ").append(Color.getColor(d[21])).append(" ").append(Color.getColor(d[22])).append(" ").append(Color.getColor(d[23])).append(" ").append(Color.getColor(d[30])).append(" ").append(Color.getColor(d[31])).append(" ").append(Color.getColor(d[32])).append(" ").append(Color.getColor(d[39])).append(" ").append(Color.getColor(d[40])).append(" ").append(Color.getColor(d[41])).append("\n");
        display.append(Color.getColor(d[15])).append(" ").append(Color.getColor(d[16])).append(" ").append(Color.getColor(d[17])).append(" ").append(Color.getColor(d[24])).append(" ").append(Color.getColor(d[25])).append(" ").append(Color.getColor(d[26])).append(" ").append(Color.getColor(d[33])).append(" ").append(Color.getColor(d[34])).append(" ").append(Color.getColor(d[35])).append(" ").append(Color.getColor(d[42])).append(" ").append(Color.getColor(d[43])).append(" ").append(Color.getColor(d[44])).append("\n");
        display.append("      ").append(Color.getColor(d[45])).append(" ").append(Color.getColor(d[46])).append(" ").append(Color.getColor(d[47])).append("\n");
        display.append("      ").append(Color.getColor(d[48])).append(" ").append(Color.getColor(d[49])).append(" ").append(Color.getColor(d[50])).append("\n");
        display.append("      ").append(Color.getColor(d[51])).append(" ").append(Color.getColor(d[52])).append(" ").append(Color.getColor(d[53])).append("\n");

        System.out.println(display.toString());
    }

    public void displayMoves() {
        StringBuilder output = new StringBuilder();
        for (CubeRotation rotation : this.rotations) {
            String[] data = rotation.toString().split("_");
            output.append(data[0].charAt(0)).append(data.length == 2 ? "'" : "").append("   ");
        }
        System.out.println(output.toString());
    }

    public Cubit[] getCubits() { return this.cubits; }

    public int getMoves() { return this.moves; }
    public void resetMoves() { this.moves = 0; this.rotations.clear(); }
}
