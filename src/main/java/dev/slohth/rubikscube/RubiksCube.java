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

        Benchmark benchmark = new Benchmark(TimeUnit.MICROSECONDS);
        benchmark.start();

        int moves = 0;
        for (int i = 0; i < 1000; i++) {
            test();
            moves += cube.getMoves();
            cube.resetMoves();
        }
        System.out.println(moves);

        double averageTime = ((double) benchmark.stop()) / 1000;
        double averageMoves = ((double) moves) / 1000;

        System.out.println("Solved in " + averageMoves + " moves in " + averageTime + " microseconds");
        cube.display();

//        test();
//        cube.display();
//        System.out.println("Solved: " + cube.isSolved());

    }

    private void test() {
        for (int i = 0; i < 50; i++) {
            this.cube.rotate(CubeRotation.random());
        }
        this.solver.solveBottomCross();
        this.solver.solveBottomLayer();
        this.solver.firstTwoLayers();
        this.solver.solveTopCross();
        this.solver.solveTopCorners();
        this.solver.orientLastLayer();
    }

}
