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

        int moves = 0;
        Benchmark b = new Benchmark(TimeUnit.MICROSECONDS);
        b.start();
        for (int i = 0; i < 25; i++) {
            test();
            moves += cube.moves;
            cube.moves = 0;
        }
        b.stop();

        System.out.println("Bottom layer solve in average of " + (double) moves / 25 + " moves in " + (double) b.stop() / 25 + " microseconds");

//        test();
        //cube.display();

    }

    private void test() {
        for (int i = 0; i < 50; i++) {
            this.cube.rotate(CubeRotation.random());
        }
        this.solver.solveBottomCross();
        this.solver.solveBottomLayer();
        this.solver.firstTwoLayers();
    }

}
