package dev.slohth.rubikscube;

import dev.slohth.rubikscube.cube.Cube;
import dev.slohth.rubikscube.cube.CubeRotation;
import dev.slohth.rubikscube.solver.CubeSolver;

public final class RubiksCube {

    public static void main(String[] args) { new RubiksCube().run(); }

    public void run() {

        Cube cube = new Cube();

        for (int i = 0; i < 20; i++) {
            cube.rotate(CubeRotation.random());
        }
        cube.display();
        System.out.println(" ");

        CubeSolver solver = new CubeSolver(cube);
        solver.solveRedCross();

        cube.display();

    }
}
