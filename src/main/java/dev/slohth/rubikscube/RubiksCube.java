package dev.slohth.rubikscube;

import dev.slohth.rubikscube.cube.Cube;
import dev.slohth.rubikscube.cube.CubeRotation;

public final class RubiksCube {

    public static void main(String[] args) { new RubiksCube().run(); }

    public void run() {

        /*

        2 is FRONT
        all rotations are described relative to the FRONT
        aka RIGHT, is moving side 2 to the RIGHT

          0
        1 2 3 4
          5
         (4)

         */

        Cube cube = new Cube();
        cube.rotate(CubeRotation.RIGHT);
        cube.display();

    }

    private byte[] rotate(byte[] arr) {
        return new byte[] { arr[6], arr[3], arr[0], arr[7], arr[4], arr[1], arr[8], arr[5], arr[2] };
    }
}
