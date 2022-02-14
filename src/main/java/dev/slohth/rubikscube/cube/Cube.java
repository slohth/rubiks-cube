package dev.slohth.rubikscube.cube;

import dev.slohth.rubikscube.cube.move.Move;
import dev.slohth.rubikscube.cubit.Cubit;
import dev.slohth.rubikscube.cubit.CubitRotation;

import java.util.*;

public class Cube {

    private final Cubit[] cubits;
    private List<Move> moves = new ArrayList<>();

    public Cube() {
        this.cubits = new Cubit[27];
        for (int i = 0; i < this.cubits.length; i++) this.cubits[i] = new Cubit(this, i);
    }

    public Cube(int[] input) {
        this.cubits = new Cubit[27];
        for (int i = 0; i < this.cubits.length; i++) this.cubits[i] = new Cubit(this, i);

        // STORE INPUTS INTO SPLIT ARRAYS PER FACE
        int[][] faces = new int[6][9];

        int face = 0;
        for (int n = 0; n < 54; n++) {
            if (n != 0 && n % 9 == 0) face++;
            faces[face][n - (face * 9)] = input[n];
        }

        // STORE RELATIVE INPUT
        byte[][] cubitsToArrange = new byte[27][6];

        for (int cubit = 0; cubit < 27; cubit++) {
            byte[] arr = new byte[] {-1, -1, -1, -1, -1, -1};

            for (CubeFace cubeFace : CubeFace.values()) {
                for (int index = 0; index < cubeFace.getCubits().length; index++) {
                    if (cubeFace.getCubits()[index] == cubit) {
                        arr[cubeFace.getId()] = (byte) faces[cubeFace.getId()][index];
                    }
                }
            }

            cubitsToArrange[cubit] = arr;
        }

        // APPLY INPUTS TO CUBIT
        for (int i = 0; i < 27; i++) {
            Cubit cubit = cubits[i];
            byte[] arrange = cubitsToArrange[i];
            //System.out.println(Arrays.toString(arrange));
            cubit.giveInput(arrange);
        }
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
        moves.add(new Move(rotation));
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

    public String getMovesDisplay() {
        StringBuilder output = new StringBuilder();

        for (Move move : simplify(moves)) {
            output.append(move.getType().toString().charAt(0));
            if (move.getCount() < 0 && move.getCount() != -3) output.append("'");
            if (move.getCount() != 1 && move.getCount() != -1) output.append(Math.abs(move.getCount()));
            output.append("   ");
        }

        return output.toString();
    }

    public Cubit[] getCubits() { return this.cubits; }

    public void resetMoves() { this.moves.clear(); }

    private List<Move> simplify(List<Move> moves) {
        boolean changed = false;

        // REMOVING
        List<Move> toRemove = new ArrayList<>();
        for (Move move : moves) {
            if (move.getCount() == 0) {
                changed = true; toRemove.add(move);
            }
        }
        moves.removeAll(toRemove);

        // MERGING
        List<Move> simplified = new ArrayList<>();
        List<Move> toIgnore = new ArrayList<>();

        for (int i = 0; i < moves.size(); i++) {
            Move current = moves.get(i);
            if (toIgnore.contains(current)) continue;

            if (i == moves.size() - 1) {
                simplified.add(current);
            } else {
                int n = i + 1;

                while (n <= moves.size() - 2) {
                    if (current.getType().isOpposite(moves.get(n).getType()) || toIgnore.contains(moves.get(n))) {
                        n++;
                    } else {
                        break;
                    }
                }

                if (current.compare(moves.get(n))) {
                    changed = true;
                    simplified.add(Move.add(current, moves.get(n)));
                    toIgnore.add(moves.get(n));
                } else {
                    simplified.add(current);
                }
            }
        }

        return (changed ? simplify(simplified) : simplified);
    }
}
