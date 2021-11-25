package dev.slohth.rubikscube.solver;

import dev.slohth.rubikscube.cube.Cube;
import dev.slohth.rubikscube.cube.CubeFace;
import dev.slohth.rubikscube.cube.CubeRotation;
import dev.slohth.rubikscube.cubit.Cubit;

import java.util.HashSet;
import java.util.Set;

public class CubeSolver {

    private final Cube cube;

    public CubeSolver(Cube cube) { this.cube = cube; }

    public void solveRedCross() {

        this.moveDownEdgesToTop();

        while (!this.crossIsOnTop()) this.moveFaceEdgesToTop();

    }

    private void moveDownEdgesToTop() {
        int[] data = new int[] { 1, 3, 5, 7 };
        int[] adj = new int[] { 7, 3, 5, 1 };
        CubeRotation[] rots = new CubeRotation[] { CubeRotation.FRONT, CubeRotation.LEFT, CubeRotation.RIGHT, CubeRotation.BACK };

        for (Cubit edge : getRedEdgesOnFace(CubeFace.DOWN)) {
            int pos = edge.getIndexInCube();
            for (int i = 0; i < data.length; i++) {
                if (CubeFace.DOWN.getCubits()[data[i]] == pos) {
                    while (cube.getCubits()[adj[i]].getOrientation()[0] == 5) {
                        cube.rotate(CubeRotation.UP);
                    }
                    cube.rotate(rots[i]); cube.rotate(rots[i]);
                }
            }
        }
    }

    private void moveFaceEdgesToTop() {

        int[] data = new int[] {}; int[] top = new int[] {}; CubeRotation[] rots = new CubeRotation[] {}; int topEdge = 0;
        for (CubeFace face : new CubeFace[] { CubeFace.FRONT, CubeFace.RIGHT, CubeFace.BACK, CubeFace.LEFT }) {
            switch (face) {
                case FRONT -> {
                    data = new int[] { 15, 17 }; top = new int[] { 3, 5 }; topEdge = 7;
                    rots = new CubeRotation[] { CubeRotation.LEFT_PRIME, CubeRotation.RIGHT };
                } case RIGHT -> {
                    data = new int[] { 17, 11 }; top = new int[] { 7, 1 }; topEdge = 5;
                    rots = new CubeRotation[] { CubeRotation.FRONT_PRIME, CubeRotation.BACK };
                } case BACK -> {
                    data = new int[] { 11, 9 }; top = new int[] { 5, 3 }; topEdge = 1;
                    rots = new CubeRotation[] { CubeRotation.RIGHT_PRIME, CubeRotation.LEFT };
                } case LEFT -> {
                    data = new int[] { 9, 15 }; top = new int[] { 1, 7 }; topEdge = 3;
                    rots = new CubeRotation[] { CubeRotation.BACK_PRIME, CubeRotation.FRONT };
                }
            }

            while (!getRedEdgesOnFace(face).isEmpty()) {
                System.out.println("Edge set");
                for (Cubit edge : getRedEdgesOnFace(face)) {
                    System.out.println(1);
                    int pos = edge.getIndexInCube();
                    for (int i = 0; i < data.length; i++) {
                        System.out.println(2);
                        if (face.getCubits()[new int[] { 3, 5 }[i]] == pos) {
                            if (crossIsOnTop()) return;
                            while (cube.getCubits()[data[i]].getOrientation()[0] == 5) {
                                System.out.println(3);
                                cube.rotate(CubeRotation.UP);
                            }
                            cube.rotate(rots[i]);
                        }
                    }
                }
                System.out.println(4);
                while (cube.getCubits()[topEdge].getOrientation()[0] == 5) {
                    System.out.println(5);
                    System.out.println("Orientation condition");
                    cube.rotate(CubeRotation.UP);
                }
                System.out.println(6);
                cube.rotate(CubeRotation.valueOf(face.toString()));
            }
        }


//        CubeFace face = CubeFace.RIGHT;
//
//        data = new int[]{17, 11};
//        top = new int[]{7, 1};
//        topEdge = 5;
//        rots = new CubeRotation[]{CubeRotation.FRONT_PRIME, CubeRotation.BACK};
//
//        while (!getRedEdgesOnFace(face).isEmpty()) {
//            System.out.println("Edge set");
//            for (Cubit edge : getRedEdgesOnFace(face)) {
//                System.out.println(1);
//                int pos = edge.getIndexInCube();
//                for (int i = 0; i < data.length; i++) {
//                    System.out.println(2);
//                    if (face.getCubits()[top[i]] == pos) {
//                        while (cube.getCubits()[top[i]].getOrientation()[0] == 5) {
//                            System.out.println(3);
//                            cube.rotate(CubeRotation.UP);
//                        }
//                        cube.rotate(rots[i]);
//                    }
//                }
//            }
//            System.out.println(4);
//            while (cube.getCubits()[topEdge].getOrientation()[0] == 5) {
//                System.out.println(5);
//                System.out.println("Orientation condition");
//                cube.rotate(CubeRotation.UP);
//            }
//            System.out.println(6);
//            cube.rotate(CubeRotation.valueOf(face.toString()));
//        }

    }

    private boolean crossIsOnTop() {
        return getRedEdgesOnFace(CubeFace.UP).size() == 4;
    }

    private Set<Cubit> getRedEdgesOnFace(CubeFace face) {
        Set<Cubit> edges = new HashSet<>();
        byte[] cubits = face.getCubits();
        for (int index : new int[] { 1, 3, 5, 7 }) {
            if (cube.getCubits()[cubits[index]].getOrientation()[face.getId()] == 5) edges.add(cube.getCubits()[cubits[index]]);
        }
        return edges;
    }

}
