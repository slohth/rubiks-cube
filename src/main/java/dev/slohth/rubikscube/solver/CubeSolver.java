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

    public void solveBottomCross() {
        do {
            this.moveDownEdgesToTop();
            this.moveFaceEdgesToTop();
        } while (this.getBottomEdgesOnFace(CubeFace.UP).size() < 4);
        this.allignTopCrossToBottom();
    }

    private void moveDownEdgesToTop() {
        int[] data = new int[] { 1, 3, 5, 7 };
        int[] adj = new int[] { 7, 3, 5, 1 };
        CubeRotation[] rots = new CubeRotation[] { CubeRotation.FRONT, CubeRotation.LEFT, CubeRotation.RIGHT, CubeRotation.BACK };

        for (Cubit edge : getBottomEdgesOnFace(CubeFace.DOWN)) {
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

        for (CubeFace face : new CubeFace[] { CubeFace.FRONT, CubeFace.RIGHT, CubeFace.BACK, CubeFace.LEFT}) {
            switch (face) {
                case FRONT -> {

                    while (!this.getBottomEdgesOnFace(face).isEmpty()) {
                        Cubit leftCheck = cube.getCubits()[face.getCubits()[3]];
                        if (leftCheck.getOrientation()[face.getId()] == 5) {
                            while (cube.getCubits()[3].getOrientation()[0] == 5) {
                                cube.rotate(CubeRotation.UP);
                            }
                            cube.rotate(CubeRotation.LEFT_PRIME);
                        }

                        Cubit rightCheck = cube.getCubits()[face.getCubits()[5]];
                        if (rightCheck.getOrientation()[face.getId()] == 5) {
                            while (cube.getCubits()[5].getOrientation()[0] == 5) {
                                cube.rotate(CubeRotation.UP);
                            }
                            cube.rotate(CubeRotation.RIGHT);
                        }

                        Cubit topCheck = cube.getCubits()[face.getCubits()[1]];
                        Cubit bottomCheck = cube.getCubits()[face.getCubits()[7]];
                        if (topCheck.getOrientation()[face.getId()] == 5) {
                            cube.rotate(CubeRotation.FRONT);
                        } else if (bottomCheck.getOrientation()[face.getId()] == 5) {
                            while (cube.getCubits()[7].getOrientation()[0] == 5) {
                                cube.rotate(CubeRotation.UP);
                            }
                            cube.rotate(CubeRotation.FRONT);
                        }
                    }

                }
                case RIGHT -> {

                    while (!this.getBottomEdgesOnFace(face).isEmpty()) {
                        Cubit leftCheck = cube.getCubits()[face.getCubits()[3]];
                        if (leftCheck.getOrientation()[face.getId()] == 5) {
                            while (cube.getCubits()[7].getOrientation()[0] == 5) {
                                cube.rotate(CubeRotation.UP);
                            }
                            cube.rotate(CubeRotation.FRONT_PRIME);
                        }

                        Cubit rightCheck = cube.getCubits()[face.getCubits()[5]];
                        if (rightCheck.getOrientation()[face.getId()] == 5) {
                            while (cube.getCubits()[1].getOrientation()[0] == 5) {
                                cube.rotate(CubeRotation.UP);
                            }
                            cube.rotate(CubeRotation.BACK);
                        }

                        Cubit topCheck = cube.getCubits()[face.getCubits()[1]];
                        Cubit bottomCheck = cube.getCubits()[face.getCubits()[7]];
                        if (topCheck.getOrientation()[face.getId()] == 5) {
                            cube.rotate(CubeRotation.RIGHT);
                        } else if (bottomCheck.getOrientation()[face.getId()] == 5) {
                            while (cube.getCubits()[5].getOrientation()[0] == 5) {
                                cube.rotate(CubeRotation.UP);
                            }
                            cube.rotate(CubeRotation.RIGHT);
                        }
                    }

                }
                case BACK -> {

                    while (!this.getBottomEdgesOnFace(face).isEmpty()) {
                        Cubit leftCheck = cube.getCubits()[face.getCubits()[3]];
                        if (leftCheck.getOrientation()[face.getId()] == 5) {
                            while (cube.getCubits()[5].getOrientation()[0] == 5) {
                                cube.rotate(CubeRotation.UP);
                            }
                            cube.rotate(CubeRotation.RIGHT_PRIME);
                        }

                        Cubit rightCheck = cube.getCubits()[face.getCubits()[5]];
                        if (rightCheck.getOrientation()[face.getId()] == 5) {
                            while (cube.getCubits()[3].getOrientation()[0] == 5) {
                                cube.rotate(CubeRotation.UP);
                            }
                            cube.rotate(CubeRotation.LEFT);
                        }

                        Cubit topCheck = cube.getCubits()[face.getCubits()[1]];
                        Cubit bottomCheck = cube.getCubits()[face.getCubits()[7]];
                        if (topCheck.getOrientation()[face.getId()] == 5) {
                            cube.rotate(CubeRotation.BACK);
                        } else if (bottomCheck.getOrientation()[face.getId()] == 5) {
                            while (cube.getCubits()[1].getOrientation()[0] == 5) {
                                cube.rotate(CubeRotation.UP);
                            }
                            cube.rotate(CubeRotation.BACK);
                        }
                    }

                }
                case LEFT -> {

                    while (!this.getBottomEdgesOnFace(face).isEmpty()) {
                        Cubit leftCheck = cube.getCubits()[face.getCubits()[3]];
                        if (leftCheck.getOrientation()[face.getId()] == 5) {
                            while (cube.getCubits()[1].getOrientation()[0] == 5) {
                                cube.rotate(CubeRotation.UP);
                            }
                            cube.rotate(CubeRotation.BACK_PRIME);
                        }

                        Cubit rightCheck = cube.getCubits()[face.getCubits()[5]];
                        if (rightCheck.getOrientation()[face.getId()] == 5) {
                            while (cube.getCubits()[7].getOrientation()[0] == 5) {
                                cube.rotate(CubeRotation.UP);
                            }
                            cube.rotate(CubeRotation.FRONT);
                        }

                        Cubit topCheck = cube.getCubits()[face.getCubits()[1]];
                        Cubit bottomCheck = cube.getCubits()[face.getCubits()[7]];
                        if (topCheck.getOrientation()[face.getId()] == 5) {
                            cube.rotate(CubeRotation.LEFT);
                        } else if (bottomCheck.getOrientation()[face.getId()] == 5) {
                            while (cube.getCubits()[3].getOrientation()[0] == 5) {
                                cube.rotate(CubeRotation.UP);
                            }
                            cube.rotate(CubeRotation.LEFT);
                        }
                    }

                }
            }
        }

    }

    private void allignTopCrossToBottom() {

        int[] edges = new int[] { 3, 7, 5, 1 };
        for (int i = 0; i < edges.length; i++) {

            for (int check = 0; check < 4; check++) {
                if (cube.getCubits()[CubeFace.UP.getCubits()[edges[i]]].getOrientation()[0] != 5) {
                    cube.rotate(CubeRotation.UP);
                } else {
                    Cubit c = cube.getCubits()[CubeFace.UP.getCubits()[edges[i]]];
                    if (c.getOrientation()[i + 1] == (i + 1)) {
                        CubeRotation rot = CubeRotation.valueOf(CubeFace.getById(i + 1).toString());
                        cube.rotate(rot); cube.rotate(rot);
                    } else {
                        cube.rotate(CubeRotation.UP);
                    }
                }
            }

        }

    }

    private void moveTopLayerCornersToBottom() {
        while (this.getBottomCornersOnTopLayers().size() > 0) {

            for (CubeFace face : new CubeFace[] { CubeFace.FRONT, CubeFace.RIGHT, CubeFace.BACK, CubeFace.LEFT}) {

                CubeRotation[] moves = new CubeRotation[4];
                switch (face) {
                    case FRONT -> { moves = new CubeRotation[] { CubeRotation.LEFT_PRIME, CubeRotation.LEFT, CubeRotation.RIGHT, CubeRotation.RIGHT_PRIME }; }
                    case RIGHT -> { moves = new CubeRotation[] { CubeRotation.FRONT_PRIME, CubeRotation.FRONT, CubeRotation.BACK, CubeRotation.BACK_PRIME }; }
                    case BACK -> { moves = new CubeRotation[] { CubeRotation.RIGHT_PRIME, CubeRotation.RIGHT, CubeRotation.LEFT, CubeRotation.LEFT_PRIME }; }
                    case LEFT -> { moves = new CubeRotation[] { CubeRotation.BACK_PRIME, CubeRotation.BACK, CubeRotation.FRONT, CubeRotation.FRONT_PRIME }; }
                }

                Cubit topLeft = cube.getCubits()[face.getCubits()[0]];
                if (topLeft.getOrientation()[face.getId()] == 5) {
                    if (topLeft.getOrientation()[0] == face.getId()) {
                        cube.rotate(CubeRotation.UP_PRIME);
                        cube.rotate(moves[0]);
                        cube.rotate(CubeRotation.UP);
                        cube.rotate(moves[1]);
                    }
                }

                Cubit topRight = cube.getCubits()[face.getCubits()[2]];
                if (topRight.getOrientation()[face.getId()] == 5) {
                    if (topRight.getOrientation()[0] == face.getId()) {
                        cube.rotate(CubeRotation.UP);
                        cube.rotate(moves[2]);
                        cube.rotate(CubeRotation.UP_PRIME);
                        cube.rotate(moves[3]);
                    }
                }

            }
            cube.rotate(CubeRotation.UP);

        }
    }

    public void solveBottomLayer() {
        do {
            moveBottomLayerCornersToTopLayer();
            moveTopLayerCornersToBottom();
            moveTopCornersToTopLayer();
            moveTopLayerCornersToBottom();
        } while (!this.bottomLayerSolved());
    }

    private void moveTopCornersToTopLayer() {
        while (this.getBottomCornersOnTop().size() > 0) {

            for (CubeFace face : new CubeFace[] { CubeFace.FRONT, CubeFace.LEFT, CubeFace.BACK, CubeFace.RIGHT }) {

                CubeRotation[] moves = new CubeRotation[2];
                int bottom = 0;
                switch (face) {
                    case FRONT -> { moves = new CubeRotation[] { CubeRotation.LEFT_PRIME, CubeRotation.LEFT }; bottom = 8; }
                    case RIGHT -> { moves = new CubeRotation[] { CubeRotation.FRONT_PRIME, CubeRotation.FRONT }; bottom = 2; }
                    case BACK -> { moves = new CubeRotation[] { CubeRotation.RIGHT_PRIME, CubeRotation.RIGHT }; bottom = 0; }
                    case LEFT -> { moves = new CubeRotation[] { CubeRotation.BACK_PRIME, CubeRotation.BACK }; bottom = 6; }
                }

                Cubit bottomRight = cube.getCubits()[CubeFace.UP.getCubits()[bottom]];
                if (bottomRight.getOrientation()[0] == 5) {
                    if (bottomRight.getOrientation()[face.getId()] == face.getId()) {
                        cube.rotate(CubeRotation.UP);
                        cube.rotate(moves[0]);
                        cube.rotate(CubeRotation.UP);
                        cube.rotate(CubeRotation.UP);
                        cube.rotate(moves[1]);
                    } else {
                        cube.rotate(CubeRotation.UP);
                    }

                }

            }

            while (this.getBottomCornersOnTopLayers().size() > 0) this.moveTopLayerCornersToBottom();

        }
    }

    private void moveBottomLayerCornersToTopLayer() {
        for (CubeFace face : new CubeFace[] { CubeFace.FRONT, CubeFace.LEFT, CubeFace.BACK, CubeFace.RIGHT }) {

            CubeRotation faceRot = CubeRotation.valueOf(face.toString());
            CubeRotation faceRotPrime = CubeRotation.valueOf(face.toString() + "_PRIME");

            Cubit bottomLeft = cube.getCubits()[face.getCubits()[6]];
            if (bottomLeft.getOrientation()[face.getId()] == 5) {
                cube.rotate(faceRot);
                cube.rotate(CubeRotation.UP);
                cube.rotate(CubeRotation.UP);
                cube.rotate(faceRotPrime);
            }

            Cubit bottomRight = cube.getCubits()[face.getCubits()[8]];
            if (bottomRight.getOrientation()[face.getId()] == 5) {
                cube.rotate(faceRotPrime);
                cube.rotate(CubeRotation.UP);
                cube.rotate(CubeRotation.UP);
                cube.rotate(faceRot);
            }

        }
    }

    private Set<Cubit> getBottomEdgesOnFace(CubeFace face) {
        Set<Cubit> edges = new HashSet<>();
        byte[] cubits = face.getCubits();
        for (int index : new int[] { 1, 3, 5, 7 }) {
            Cubit c = cube.getCubits()[cubits[index]];
            if (c.getOrientation()[face.getId()] == 5) edges.add(c);
        }
        return edges;
    }

    private Set<Cubit> getBottomCornersOnTopLayers() {
        Set<Cubit> edges = new HashSet<>();
        for (CubeFace face : new CubeFace[] { CubeFace.LEFT, CubeFace.FRONT, CubeFace.RIGHT, CubeFace.BACK }) {
            byte[] cubits = face.getCubits();
            for (int index : new int[] { 0, 2 }) {
                Cubit c = cube.getCubits()[cubits[index]];
                if (c.getOrientation()[face.getId()] == 5) edges.add(c);
            }
        }
        return edges;
    }

    private Set<Cubit> getBottomCornersOnTop() {
        Set<Cubit> edges = new HashSet<>();
        byte[] cubits = CubeFace.UP.getCubits();
        for (int index : new int[] { 0, 2, 6, 8 }) {
            Cubit c = cube.getCubits()[cubits[index]];
            if (c.getOrientation()[0] == 5) edges.add(c);
        }
        return edges;
    }

    private boolean bottomLayerSolved() {
        for (int i = 0; i < 9; i++) {
            if (cube.getCubits()[CubeFace.DOWN.getCubits()[i]].getOrientation()[5] != 5) return false;
        }
        return true;
    }

    public void firstTwoLayers() {

    }

}
