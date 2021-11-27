package dev.slohth.rubikscube.cube;

import dev.slohth.rubikscube.cubit.Cubit;
import dev.slohth.rubikscube.cubit.CubitRotation;

public class Cube {

    private final Cubit[] cubits;

    public int moves;

    public Cube() {
        this.cubits = new Cubit[27];
        for (int i = 0; i < this.cubits.length; i++) this.cubits[i] = new Cubit(this, i);
    }

    public boolean isSolved() {
        for (Cubit cubit : this.cubits) if (!cubit.isSolved()) return false;
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

        System.out.println("      " + d[0] + " " + d[1] + " " + d[2]);
        System.out.println("      " + d[3] + " " + d[4] + " " + d[5]);
        System.out.println("      " + d[6] + " " + d[7] + " " + d[8]);
        System.out.println(d[9] + " " + d[10] + " " + d[11] + " " + d[18] + " " + d[19] + " " + d[20] + " " + d[27] + " " + d[28] + " " + d[29] + " " + d[36] + " " + d[37] + " " + d[38]);
        System.out.println(d[12] + " " + d[13] + " " + d[14] + " " + d[21] + " " + d[22] + " " + d[23] + " " + d[30] + " " + d[31] + " " + d[32] + " " + d[39] + " " + d[40] + " " + d[41]);
        System.out.println(d[15] + " " + d[16] + " " + d[17] + " " + d[24] + " " + d[25] + " " + d[26] + " " + d[33] + " " + d[34] + " " + d[35] + " " + d[42] + " " + d[43] + " " + d[44]);
        System.out.println("      " + d[45] + " " + d[46] + " " + d[47]);
        System.out.println("      " + d[48] + " " + d[49] + " " + d[50]);
        System.out.println("      " + d[51] + " " + d[52] + " " + d[53]);

    }

    public Cubit[] getCubits() { return this.cubits; }
}
