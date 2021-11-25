package dev.slohth.rubikscube.cube;

import dev.slohth.rubikscube.cubit.CubitRotation;

import java.util.concurrent.ThreadLocalRandom;

public enum CubeRotation {

    UP(CubeFace.UP, new CubitRotation[] { CubitRotation.LEFT }, false),
    UP_PRIME(CubeFace.UP, new CubitRotation[] { CubitRotation.RIGHT }, true),

    DOWN(CubeFace.DOWN, new CubitRotation[] { CubitRotation.RIGHT }, false),
    DOWN_PRIME(CubeFace.DOWN, new CubitRotation[] { CubitRotation.LEFT }, true),

    LEFT(CubeFace.LEFT, new CubitRotation[] { CubitRotation.DOWN }, false),
    LEFT_PRIME(CubeFace.LEFT, new CubitRotation[] { CubitRotation.UP }, true),

    RIGHT(CubeFace.RIGHT, new CubitRotation[] { CubitRotation.UP }, false),
    RIGHT_PRIME(CubeFace.RIGHT, new CubitRotation[] { CubitRotation.DOWN }, true),

    FRONT(CubeFace.FRONT, new CubitRotation[] { CubitRotation.DOWN, CubitRotation.RIGHT, CubitRotation.UP }, false),
    FRONT_PRIME(CubeFace.FRONT, new CubitRotation[] { CubitRotation.DOWN, CubitRotation.LEFT, CubitRotation.UP }, true),

    BACK(CubeFace.BACK, new CubitRotation[] { CubitRotation.DOWN, CubitRotation.LEFT, CubitRotation.UP }, false),
    BACK_PRIME(CubeFace.BACK, new CubitRotation[] { CubitRotation.DOWN, CubitRotation.RIGHT, CubitRotation.UP }, true);

    private final CubeFace face;
    private final CubitRotation[] rotations;
    private final boolean prime;

    CubeRotation(CubeFace face, CubitRotation[] rotations, boolean prime) {
        this.face = face;
        this.rotations = rotations;
        this.prime = prime;
    }

    public CubitRotation[] getRotations() { return this.rotations; }

    public boolean isPrime() { return this.prime; }

    public CubeFace getFace() { return this.face; }

    public static CubeRotation random() {
        return CubeRotation.values()[ThreadLocalRandom.current().nextInt(CubeRotation.values().length)];
    }
}
