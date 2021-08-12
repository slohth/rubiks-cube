package dev.slohth.rubikscube;

import dev.slohth.rubikscube.cube.Cube;
import dev.slohth.rubikscube.cube.CubeRotation;

public final class RubiksCube {

    public static void main(String[] args) { new RubiksCube().run(); }

    public void run() {

        Cube cube = new Cube();
        cube.rotate(CubeRotation.RIGHT);
        cube.display();

    }
}
