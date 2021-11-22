package dev.slohth.rubikscube;

import dev.slohth.rubikscube.cube.Cube;
import dev.slohth.rubikscube.cube.CubeRotation;

public final class RubiksCube {

    public static void main(String[] args) { new RubiksCube().run(); }

    public void run() {

        Cube cube = new Cube();

        System.out.println(cube.isSolved());

        cube.rotate(CubeRotation.UP);
        cube.rotate(CubeRotation.UP_PRIME);
        System.out.println(cube.isSolved());

//        // Random scramble
//        for (int i = 0; i < 50; i++) {
//            cube.rotate(CubeRotation.random());
//        }
//
//        int moves = 0;
//        Benchmark benchmark = new Benchmark();
//        benchmark.start();
//        while (!cube.isSolved()) {
//            cube.rotate(CubeRotation.random());
//            moves++;
//            System.out.println("Rotated! " + moves);
//        }
//        System.out.println(moves + " moves in " + benchmark.stop() + "ms");

    }
}
