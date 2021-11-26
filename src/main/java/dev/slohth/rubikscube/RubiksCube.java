package dev.slohth.rubikscube;

import dev.slohth.rubikscube.cube.Cube;
import dev.slohth.rubikscube.cube.CubeRotation;
import dev.slohth.rubikscube.solver.CubeSolver;

public final class RubiksCube {

    public static void main(String[] args) { new RubiksCube().run(); }

    public void run() {

        Cube cube = new Cube();

        CubeSolver solver = new CubeSolver(cube);


        for (int n = 0; n < 10; n++) {
            for (int i = 0; i < 50; i++) {
                cube.rotate(CubeRotation.random());
            }
            solver.solveRedCross();
        }


    }
}
