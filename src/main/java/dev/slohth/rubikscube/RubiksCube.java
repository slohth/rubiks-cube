package dev.slohth.rubikscube;

import dev.slohth.rubikscube.cube.Cube;
import dev.slohth.rubikscube.cube.CubeRotation;
import dev.slohth.rubikscube.cube.move.Move;
import dev.slohth.rubikscube.cube.move.MoveType;
import dev.slohth.rubikscube.cubit.Cubit;
import dev.slohth.rubikscube.cubit.CubitRotation;
import dev.slohth.rubikscube.display.Display;
import dev.slohth.rubikscube.solver.CubeSolver;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static dev.slohth.rubikscube.cube.CubeRotation.LEFT;

public final class RubiksCube {

    public static void main(String[] args) { new RubiksCube().run(); }

    private Cube cube;
    private CubeSolver solver;

    public void run() {

        this.cube = new Cube();

        this.solver = new CubeSolver(cube);

//        Benchmark benchmark = new Benchmark(TimeUnit.MICROSECONDS);
//        benchmark.start();
//
//        int moves = 0;
//        for (int i = 0; i < 1000; i++) {
//            test();
//            moves += cube.getMoves();
//            cube.resetMoves();
//        }
//
//        double averageTime = ((double) benchmark.stop()) / 1000;
//        double averageMoves = ((double) moves) / 1000;
//
//        System.out.println("Solved in " + averageMoves + " moves in " + averageTime + " microseconds");

        test2();
        //System.out.println(cube.getMoves());
        new Display();
    }

    private void test() {
        for (int i = 0; i < 50; i++) {
            this.cube.rotate(CubeRotation.random());
        }
        cube.resetMoves();

        this.solver.solveBottomCross();
        this.solver.solveBottomLayer();
        this.solver.firstTwoLayers();
        this.solver.solveTopCross();
        this.solver.solveTopCorners();
        this.solver.orientLastLayer();
    }

    private void test2() {
        cube.rotate(LEFT);
        cube.rotate(CubeRotation.UP_PRIME);
        cube.rotate(CubeRotation.UP_PRIME);
        cube.rotate(CubeRotation.LEFT_PRIME);
        cube.rotate(CubeRotation.UP_PRIME);
        cube.rotate(LEFT);
        cube.rotate(CubeRotation.UP_PRIME);
        cube.rotate(CubeRotation.LEFT_PRIME);
        cube.displayMoves();
    }

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
//            if (i != moves.size() - 1 && moves.get(i).compare(moves.get(i + 1))) {
//                changed = true;
//                simplified.add(Move.add(moves.get(i), moves.get(i + 1)));
//                i++;
//            } else {
//                simplified.add(moves.get(i));
//            }
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
