package edu.project4.transformation;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class AffineUtils {
    private AffineUtils() {

    }

    private static final int MAX_COLOR = 255;
    private static final int COUNT_OF_TRANSFORMATIONS = 50;

    public static List<AffineGenerator> getListOfAffineTransformations() {
        List<AffineGenerator> transformations = new ArrayList<>();

        int red = ThreadLocalRandom.current().nextInt(0, MAX_COLOR + 1);
        int green = ThreadLocalRandom.current().nextInt(0, MAX_COLOR + 1);
        int blue = ThreadLocalRandom.current().nextInt(0, MAX_COLOR + 1);

        Color color = new Color(red, green, blue);

        for (int i = 0; i < COUNT_OF_TRANSFORMATIONS; i++) {
            transformations.add(new AffineGenerator(color));
        }
        return transformations;
    }
}
