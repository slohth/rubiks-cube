package dev.slohth.rubikscube.cubit;
import java.util.concurrent.ThreadLocalRandom;

public enum CubitRotation {

    RIGHT, LEFT, UP, DOWN;

    public static CubitRotation random() {
        return CubitRotation.values()[ThreadLocalRandom.current().nextInt(CubitRotation.values().length)];
    }

}
