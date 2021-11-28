package dev.slohth.rubikscube;

import dev.slohth.rubikscube.cube.Cube;
import dev.slohth.rubikscube.cube.CubeRotation;
import dev.slohth.rubikscube.solver.CubeSolver;

import java.util.concurrent.TimeUnit;

public final class RubiksCube {

    public static void main(String[] args) { new RubiksCube().run(); }

    private Cube cube;
    private CubeSolver solver;

    public void run() {

        this.cube = new Cube();

        this.solver = new CubeSolver(cube);

        for (int i = 0; i < 100; i++)
            test();
        //cube.display();

    }

    private void test() {
        for (int i = 0; i < 50; i++) {
            this.cube.rotate(CubeRotation.random());
        }
        this.solver.solveBottomCross();
        this.solver.solveBottomLayer();
        //this.solver.firstTwoLayers();
    }

}
