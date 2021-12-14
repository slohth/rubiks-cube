package dev.slohth.rubikscube.solver;

import dev.slohth.rubikscube.cube.Cube;
import dev.slohth.rubikscube.cube.CubeFace;
import dev.slohth.rubikscube.cube.CubeRotation;
import dev.slohth.rubikscube.cubit.Cubit;

import java.util.HashSet;
import java.util.Set;

import static dev.slohth.rubikscube.cube.CubeRotation.BACK;
import static dev.slohth.rubikscube.cube.CubeRotation.LEFT;

/**
 * This class takes a Cube, and applies the steps of the beginner method to solve it:
 * https://ruwix.com/the-rubiks-cube/how-to-solve-the-rubiks-cube-beginners-method/
 *
 * This solves the cube in an average of 200 moves in 17 microseconds
 * @author Brandon
 */
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

    /**
     * This procedure moves all the bottom face edges from the bottom face to the top face
     * of the cube, for it to be aligned and inserted to the bottom layer
     */
    private void moveDownEdgesToTop() {
        int[] data = new int[] { 1, 3, 5, 7 };
        int[] adj = new int[] { 7, 3, 5, 1 };
        CubeRotation[] rots = new CubeRotation[] { CubeRotation.FRONT, LEFT, CubeRotation.RIGHT, BACK };

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

    /**
     * This procedure moves all the bottom face edges from a side face to the top face
     * of the cube, for it to be aligned and inserted into the bottom layer
     */
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
                            cube.rotate(BACK);
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
                            cube.rotate(LEFT);
                        }

                        Cubit topCheck = cube.getCubits()[face.getCubits()[1]];
                        Cubit bottomCheck = cube.getCubits()[face.getCubits()[7]];
                        if (topCheck.getOrientation()[face.getId()] == 5) {
                            cube.rotate(BACK);
                        } else if (bottomCheck.getOrientation()[face.getId()] == 5) {
                            while (cube.getCubits()[1].getOrientation()[0] == 5) {
                                cube.rotate(CubeRotation.UP);
                            }
                            cube.rotate(BACK);
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
                            cube.rotate(LEFT);
                        } else if (bottomCheck.getOrientation()[face.getId()] == 5) {
                            while (cube.getCubits()[3].getOrientation()[0] == 5) {
                                cube.rotate(CubeRotation.UP);
                            }
                            cube.rotate(LEFT);
                        }
                    }

                }
            }
        }

    }

    /**
     * This procedure aligns and inserts the bottom face edges on the top face
     * to the bottom face, therefore solving the cross
     */
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

    /**
     * This function returns all the bottom face edges on the respective face
     * @param face The face to check for bottom face edges
     * @return The set of all cubits found to be a bottom edge
     */
    private Set<Cubit> getBottomEdgesOnFace(CubeFace face) {
        Set<Cubit> edges = new HashSet<>();
        byte[] cubits = face.getCubits();
        for (int index : new int[] { 1, 3, 5, 7 }) {
            Cubit c = cube.getCubits()[cubits[index]];
            if (c.getOrientation()[face.getId()] == 5) edges.add(c);
        }
        return edges;
    }

    public void solveBottomLayer() {
        do {
            moveBottomLayerCornersToTopLayer();
            moveTopLayerCornersToBottom();
            moveTopCornersToTopLayer();
            moveTopLayerCornersToBottom();
        } while (!this.bottomLayerSolved());
    }

    /**
     * This procedure moves all bottom face corners in the topmost layer of
     * the cube to the bottom face in its correct position & orientation
     */
    private void moveTopLayerCornersToBottom() {
        while (this.getBottomCornersOnTopLayers().size() > 0) {

            for (CubeFace face : new CubeFace[] { CubeFace.FRONT, CubeFace.RIGHT, CubeFace.BACK, CubeFace.LEFT}) {

                CubeRotation[] moves = new CubeRotation[4];
                switch (face) {
                    case FRONT -> { moves = new CubeRotation[] { CubeRotation.LEFT_PRIME, LEFT, CubeRotation.RIGHT, CubeRotation.RIGHT_PRIME }; }
                    case RIGHT -> { moves = new CubeRotation[] { CubeRotation.FRONT_PRIME, CubeRotation.FRONT, BACK, CubeRotation.BACK_PRIME }; }
                    case BACK -> { moves = new CubeRotation[] { CubeRotation.RIGHT_PRIME, CubeRotation.RIGHT, LEFT, CubeRotation.LEFT_PRIME }; }
                    case LEFT -> { moves = new CubeRotation[] { CubeRotation.BACK_PRIME, BACK, CubeRotation.FRONT, CubeRotation.FRONT_PRIME }; }
                }

                Cubit topLeft = cube.getCubits()[face.getCubits()[0]];
                if (topLeft.getOrientation()[face.getId()] == 5) {
                    if (topLeft.getOrientation()[0] == face.getId()) {
                        //System.out.println("Moved top left corner to bottom");
                        //cube.display();
                        cube.rotate(CubeRotation.UP_PRIME);
                        cube.rotate(moves[0]);
                        cube.rotate(CubeRotation.UP);
                        cube.rotate(moves[1]);
                    }
                }

                Cubit topRight = cube.getCubits()[face.getCubits()[2]];
                if (topRight.getOrientation()[face.getId()] == 5) {
                    if (topRight.getOrientation()[0] == face.getId()) {
                        //System.out.println("Moved top right corner to bottom");
                        //cube.display();
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

    /**
     * This procedure moves all bottom face corners that are not in the topmost layer
     * to the topmost layer, so they can be oriented to their correct position
     */
    private void moveTopCornersToTopLayer() {
        while (this.getBottomCornersOnTop().size() > 0) {

            for (CubeFace face : new CubeFace[] { CubeFace.FRONT, CubeFace.LEFT, CubeFace.BACK, CubeFace.RIGHT }) {

                CubeRotation[] moves = new CubeRotation[2];
                int bottom = 0;
                switch (face) {
                    case FRONT -> { moves = new CubeRotation[] { CubeRotation.LEFT_PRIME, LEFT }; bottom = 8; }
                    case RIGHT -> { moves = new CubeRotation[] { CubeRotation.FRONT_PRIME, CubeRotation.FRONT }; bottom = 2; }
                    case BACK -> { moves = new CubeRotation[] { CubeRotation.RIGHT_PRIME, CubeRotation.RIGHT }; bottom = 0; }
                    case LEFT -> { moves = new CubeRotation[] { CubeRotation.BACK_PRIME, BACK }; bottom = 6; }
                }

                Cubit bottomRight = cube.getCubits()[CubeFace.UP.getCubits()[bottom]];
                if (bottomRight.getOrientation()[0] == 5) {
                    if (bottomRight.getOrientation()[face.getId()] == face.getId()) {
                        //System.out.println("Moved top corner to top layer");
                        //cube.display();
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

    /**
     * This procedure moves all bottom face corners in the bottommost layer to the
     * topmost layer, so it can be oriented to its correct position
     */
    private void moveBottomLayerCornersToTopLayer() {
        for (CubeFace face : new CubeFace[] { CubeFace.FRONT, CubeFace.LEFT, CubeFace.BACK, CubeFace.RIGHT }) {

            CubeRotation faceRot = CubeRotation.valueOf(face.toString());
            CubeRotation faceRotPrime = CubeRotation.valueOf(face.toString() + "_PRIME");

            Cubit bottomLeft = cube.getCubits()[face.getCubits()[6]];
            if (bottomLeft.getOrientation()[face.getId()] != face.getId()) {
                //System.out.println("Moved bottom left corner to top layer");
                //cube.display();
                cube.rotate(faceRot);
                cube.rotate(CubeRotation.UP);
                cube.rotate(CubeRotation.UP);
                cube.rotate(faceRotPrime);
            }

            Cubit bottomRight = cube.getCubits()[face.getCubits()[8]];
            if (bottomRight.getOrientation()[face.getId()] != face.getId()) {
                //System.out.println("Moved bottom right corner to top layer");
                //cube.display();
                cube.rotate(faceRotPrime);
                cube.rotate(CubeRotation.UP);
                cube.rotate(CubeRotation.UP);
                cube.rotate(faceRot);
            }

        }
    }

    /**
     * This function returns all the bottom face corners that are on the topmost layer
     * @return A set of all the bottom face cubits
     */
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

    /**
     * This function returns all the bottom face corners that are on the top face
     * @return A set of all the bottom face cubits
     */
    private Set<Cubit> getBottomCornersOnTop() {
        Set<Cubit> edges = new HashSet<>();
        byte[] cubits = CubeFace.UP.getCubits();
        for (int index : new int[] { 0, 2, 6, 8 }) {
            Cubit c = cube.getCubits()[cubits[index]];
            if (c.getOrientation()[0] == 5) edges.add(c);
        }
        return edges;
    }

    /**
     * This function checks whether the bottom layer is solved, to terminate the
     * while loop
     * @return Whether the bottom layer is solved or not
     */
    private boolean bottomLayerSolved() {
        for (int i = 0; i < 9; i++) {
            if (cube.getCubits()[CubeFace.DOWN.getCubits()[i]].getOrientation()[5] != 5) return false;
        }
        for (CubeFace face : new CubeFace[] { CubeFace.FRONT, CubeFace.RIGHT, CubeFace.BACK, CubeFace.LEFT }) {
            for (int i = 6; i < 9; i++) {
                if (cube.getCubits()[face.getCubits()[i]].getOrientation()[face.getId()] != face.getId()) return false;
            }
        }
        return true;
    }

    public void firstTwoLayers() {
        this.orientTopLayerEdges();
        this.orientMiddleLayerEdges();
    }

    /**
     * This procedure orients and inserts all the F2L edge pieces in the
     * topmost layer, using the F2L algorithm
     */
    private void orientTopLayerEdges() {
        CubeFace[] faces = new CubeFace[] { CubeFace.LEFT, CubeFace.FRONT, CubeFace.RIGHT, CubeFace.BACK };

        while (this.getEdgesOnTopLayers().size() > 0) {
            for (int i = 0; i < faces.length; i++) {
                CubeFace face = faces[i];
                Cubit cubit = cube.getCubits()[face.getCubits()[1]];
                if (this.getEdgesOnTopLayers().contains(cubit)) {
                    if (cubit.getOrientation()[face.getId()] == face.getId()) {
                        boolean left = cubit.getOrientation()[0] == faces[i == 0 ? 3 : i - 1].getId();
                        this.f2lAlgorithm(face, left);
                    }
                }
            }

            cube.rotate(CubeRotation.UP);

        }

    }

    /**
     * This function validates the orientation of all the F2L edge pieces,
     * and orients any that are in the incorrect position
     */
    private void orientMiddleLayerEdges() {
        CubeFace[] faces = new CubeFace[] { CubeFace.LEFT, CubeFace.FRONT, CubeFace.RIGHT, CubeFace.BACK };

        for (int i = 0; i < faces.length; i++) {
            CubeFace face = faces[i];

            Cubit leftEdge = cube.getCubits()[face.getCubits()[3]];
            int leftId = faces[i == 0 ? 3 : i - 1].getId();
            if (!(leftEdge.getOrientation()[leftId] == leftId && leftEdge.getOrientation()[faces[i].getId()] == faces[i].getId())) {
                this.f2lAlgorithm(face, true);
                this.orientTopLayerEdges();
            }

            Cubit rightEdge = cube.getCubits()[face.getCubits()[5]];
            int rightId = faces[i == 3 ? 0 : i + 1].getId();
            if (!(rightEdge.getOrientation()[rightId] == rightId && rightEdge.getOrientation()[faces[i].getId()] == faces[i].getId())) {
                this.f2lAlgorithm(face, false);
                this.orientTopLayerEdges();
            }
        }
    }

    /**
     * This procedure applies the F2L algorithm to a given face, and whether it should
     * orient the edge to the left or the right
     * @param face The face to apply the algorithm
     * @param left Whether this algorithm should orient to the left
     */
    private void f2lAlgorithm(CubeFace face, boolean left) {
        CubeRotation fRot = CubeRotation.valueOf(face.toString());
        CubeRotation fRotPrime = CubeRotation.valueOf(face.toString() + "_PRIME");

        CubeRotation[] moves = new CubeRotation[2];
        switch (face) {
            case FRONT -> { moves = new CubeRotation[] { CubeRotation.LEFT_PRIME, LEFT, CubeRotation.RIGHT, CubeRotation.RIGHT_PRIME }; }
            case RIGHT -> { moves = new CubeRotation[] { CubeRotation.FRONT_PRIME, CubeRotation.FRONT, BACK, CubeRotation.BACK_PRIME }; }
            case BACK -> { moves = new CubeRotation[] { CubeRotation.RIGHT_PRIME, CubeRotation.RIGHT, LEFT, CubeRotation.LEFT_PRIME }; }
            case LEFT -> { moves = new CubeRotation[] { CubeRotation.BACK_PRIME, BACK, CubeRotation.FRONT, CubeRotation.FRONT_PRIME }; }
        }

        if (left) {
            cube.rotate(CubeRotation.UP_PRIME); cube.rotate(moves[0]);
            cube.rotate(CubeRotation.UP); cube.rotate(moves[1]);
            cube.rotate(CubeRotation.UP); cube.rotate(fRot);
            cube.rotate(CubeRotation.UP_PRIME); cube.rotate(fRotPrime);
        } else {
            cube.rotate(CubeRotation.UP); cube.rotate(moves[2]);
            cube.rotate(CubeRotation.UP_PRIME); cube.rotate(moves[3]);
            cube.rotate(CubeRotation.UP_PRIME); cube.rotate(fRotPrime);
            cube.rotate(CubeRotation.UP); cube.rotate(fRot);
        }
    }

    /**
     * This function returns all the F2L edge pieces that are in the topmost layer,
     * ready to be orientated into their respective positions
     * @return A set of all the F2L edge pieces
     */
    private Set<Cubit> getEdgesOnTopLayers() {
        Set<Cubit> edges = new HashSet<>();
        for (CubeFace face : new CubeFace[] { CubeFace.LEFT, CubeFace.FRONT, CubeFace.RIGHT, CubeFace.BACK }) {
            byte[] cubits = face.getCubits();
            Cubit c = cube.getCubits()[cubits[1]];
            if (c.getOrientation()[face.getId()] != 0 && c.getOrientation()[0] != 0) edges.add(c);
        }
        return edges;
    }

    public void solveTopCross() {
        while (!this.topCrossSolved()) {
            boolean[] state = this.getCrossState();
            if ((!state[0] && !state[1] && !state[2] && !state[3]) ||
                    (!state[0] && state[1] && state[2] && !state[3]) ||
                    (!state[0] && !state[1] && state[2] && state[3])) {
                topCrossAlgorithm();
            } else {
                cube.rotate(CubeRotation.UP);
            }
        }
    }

    /**
     * This function returns the current cross state on the top face, represented as booleans
     * @return The cross state, "true" for a correctly oriented piece
     */
    private boolean[] getCrossState() {
        boolean[] cross = new boolean[] { false, false, false, false };
        int index = 0; byte[] cubits = CubeFace.UP.getCubits();
        for (int i : new int[] { 1, 3, 5, 7 }) {
            cross[index] = cube.getCubits()[cubits[i]].getOrientation()[0] == 0;
            index++;
        }
        return cross;
    }

    /**
     * This procedure applies the algorithm for the top cross, relative to the
     * front face of the cube
     */
    private void topCrossAlgorithm() {
        cube.rotate(CubeRotation.FRONT);
        cube.rotate(CubeRotation.RIGHT);
        cube.rotate(CubeRotation.UP);
        cube.rotate(CubeRotation.RIGHT_PRIME);
        cube.rotate(CubeRotation.UP_PRIME);
        cube.rotate(CubeRotation.FRONT_PRIME);
    }

    /**
     * This function assesses whether the top cross is solved or not
     * @return Whether the top cross is solved
     */
    private boolean topCrossSolved() {
        boolean[] state = this.getCrossState();
        return (state[0] && state[1] && state[2] && state[3]);
    }

    public void solveTopCorners() {
        while (!this.topCornersSolved()) {
            boolean[] state = this.getCornersState();
            if (this.getCornersSize(state) == 1) {
                if (!state[0] && !state[1] && !state[2] && state[3]) {
                    this.topCornersAlgorithm();
                } else {
                    cube.rotate(CubeRotation.UP);
                }
            } else {
                if (this.getCornersSize(state) == 2) {
                    if ((state[0] && !state[1] && state[2] && !state[3]) || (state[0] && !state[1] && !state[2] && state[3])) {
                        this.topCornersAlgorithm();
                    } else {
                        cube.rotate(CubeRotation.UP);
                    }
                } else {
                    boolean[] corners = new boolean[] { false, false };

                    if (cube.getCubits()[CubeFace.FRONT.getCubits()[0]].getOrientation()[2] == 0) corners[0] = true;
                    if (cube.getCubits()[CubeFace.FRONT.getCubits()[2]].getOrientation()[2] == 0) corners[1] = true;

                    if (!corners[0] && !corners[1]) {
                        cube.rotate(CubeRotation.UP);
                    } else if (!corners[0]) {
                        this.topCornersAlgorithm();
                    } else if (!corners[1]) {
                        cube.rotate(LEFT);
                        cube.rotate(CubeRotation.UP_PRIME);
                        cube.rotate(CubeRotation.UP_PRIME);
                        cube.rotate(CubeRotation.LEFT_PRIME);
                        cube.rotate(CubeRotation.UP_PRIME);
                        cube.rotate(LEFT);
                        cube.rotate(CubeRotation.UP_PRIME);
                        cube.rotate(CubeRotation.LEFT_PRIME);
                    } else {
                        this.topCornersAlgorithm();
                    }
                }
            }
        }
    }

    /**
     * This function returns the current corners state on the top face, represented as booleans
     * @return The corners state, "true" for a correctly oriented piece
     */
    private boolean[] getCornersState() {
        boolean[] corners = new boolean[] { false, false, false, false };
        int index = 0; byte[] cubits = CubeFace.UP.getCubits();
        for (int i : new int[] { 0, 2, 6, 8 }) {
            corners[index] = cube.getCubits()[cubits[i]].getOrientation()[0] == 0;
            index++;
        }
        return corners;
    }

    /**
     * This function returns the number of corners that are correctly oriented on the top face
     * @param state The state of the current corners
     * @return The number of correctly oriented corners
     */
    private int getCornersSize(boolean[] state) {
        int count = 0;
        for (boolean b : state) if (b) count++;
        return count;
    }

    /**
     * This procedure applies the top corners' algorithm relative to the front face
     * of the cube
     */
    private void topCornersAlgorithm() {
        cube.rotate(CubeRotation.RIGHT_PRIME);
        cube.rotate(CubeRotation.UP);
        cube.rotate(CubeRotation.UP);
        cube.rotate(CubeRotation.RIGHT);
        cube.rotate(CubeRotation.UP);
        cube.rotate(CubeRotation.RIGHT_PRIME);
        cube.rotate(CubeRotation.UP);
        cube.rotate(CubeRotation.RIGHT);
    }

    /**
     * This function assesses whether the top corners are solved
     * @return Whether the top corners are solved or not
     */
    private boolean topCornersSolved() {
        boolean[] state = this.getCornersState();
        return (state[0] && state[1] && state[2] && state[3]);
    }

    public void orientLastLayer() {
        this.solveToHeadlights();
        do {
            this.solveLastLayer();
        } while (!cube.isSolved());
    }

    private void solveToHeadlights() {
        while (!this.headlightsReady()) {
            int h = this.getHeadlightFaceAmount();
            if (h == 0) {
                CubeFace face = null;
                for (CubeFace f : new CubeFace[] { CubeFace.LEFT, CubeFace.FRONT, CubeFace.RIGHT, CubeFace.BACK}) {
                    if (this.isSolved(f)) { face = f; break; }
                }
                if (face != null) this.moveFaceToBack(face);
                this.headlightsAlgorithm();
            } else if (h != 4) {
                CubeFace face = null;
                for (CubeFace f : new CubeFace[] { CubeFace.LEFT, CubeFace.FRONT, CubeFace.RIGHT, CubeFace.BACK}) {
                    if (this.isHeadlights(f)) { face = f; break; }
                }
                this.moveFaceToBack(face);
                this.headlightsAlgorithm();
            }
        }
    }

    private void headlightsAlgorithm() {
        cube.rotate(CubeRotation.RIGHT_PRIME);
        cube.rotate(CubeRotation.FRONT);
        cube.rotate(CubeRotation.RIGHT_PRIME);
        cube.rotate(CubeRotation.BACK);
        cube.rotate(CubeRotation.BACK);
        cube.rotate(CubeRotation.RIGHT);
        cube.rotate(CubeRotation.FRONT_PRIME);
        cube.rotate(CubeRotation.RIGHT_PRIME);
        cube.rotate(CubeRotation.BACK);
        cube.rotate(CubeRotation.BACK);
        cube.rotate(CubeRotation.RIGHT);
        cube.rotate(CubeRotation.RIGHT);
    }

    private void solveLastLayer() {
        if (this.getHeadlightFaceAmount() == 4) { this.orientEdgesAlgorithm(); return; }

        int solvedAmount = 0;
        CubeFace face = null;
        for (CubeFace f : new CubeFace[] { CubeFace.LEFT, CubeFace.FRONT, CubeFace.RIGHT, CubeFace.BACK}) {
            if (this.isSolved(f)) { face = f; solvedAmount++; }
        }

        if (solvedAmount == 4) {
            while (!cube.isSolved()) cube.rotate(CubeRotation.UP);
        } else {
            this.moveFaceToBack(face);
            this.orientEdgesAlgorithm();
        }
    }

    private void orientEdgesAlgorithm() {
        cube.rotate(CubeRotation.RIGHT);
        cube.rotate(CubeRotation.UP_PRIME);
        cube.rotate(CubeRotation.RIGHT);
        cube.rotate(CubeRotation.UP);
        cube.rotate(CubeRotation.RIGHT);
        cube.rotate(CubeRotation.UP);
        cube.rotate(CubeRotation.RIGHT);
        cube.rotate(CubeRotation.UP_PRIME);
        cube.rotate(CubeRotation.RIGHT_PRIME);
        cube.rotate(CubeRotation.UP_PRIME);
        cube.rotate(CubeRotation.RIGHT);
        cube.rotate(CubeRotation.RIGHT);
    }

    private int getHeadlightFaceAmount() {
        int headlights = 0;
        for (CubeFace face : new CubeFace[] { CubeFace.LEFT, CubeFace.FRONT, CubeFace.RIGHT, CubeFace.BACK}) {
            if (this.isHeadlights(face)) headlights++;
        }
        return headlights;
    }

    private int getSolvedFaceAmount() {
        int solved = 0;
        for (CubeFace face : new CubeFace[] { CubeFace.LEFT, CubeFace.FRONT, CubeFace.RIGHT, CubeFace.BACK}) {
            if (this.isSolved(face)) solved++;
        }
        return solved;
    }

    private void moveFaceToBack(CubeFace face) {
        int rots = 0;
        switch (face) {
            case LEFT -> { rots = 1; }
            case FRONT -> { rots = 2; }
            case RIGHT -> { rots = 3; }
        }
        for (int i = 0; i < rots; i++) cube.rotate(CubeRotation.UP);
    }

    private boolean headlightsReady() {
        return this.getHeadlightFaceAmount() == 4 || (this.getHeadlightFaceAmount() == 3 && this.getSolvedFaceAmount() == 1);
    }

    private boolean isHeadlights(CubeFace face) {
        Cubit c1 = cube.getCubits()[face.getCubits()[0]];
        Cubit c2 = cube.getCubits()[face.getCubits()[2]];
        Cubit c3 = cube.getCubits()[face.getCubits()[1]];
        return (c1.getOrientation()[face.getId()] == c2.getOrientation()[face.getId()] && c1.getOrientation()[face.getId()] != c3.getOrientation()[face.getId()]);
    }

    private boolean isSolved(CubeFace face) {
        Cubit c1 = cube.getCubits()[face.getCubits()[0]];
        Cubit c2 = cube.getCubits()[face.getCubits()[2]];
        Cubit c3 = cube.getCubits()[face.getCubits()[1]];
        return (c1.getOrientation()[face.getId()] == c2.getOrientation()[face.getId()] && c1.getOrientation()[face.getId()] == c3.getOrientation()[face.getId()]);
    }

}
