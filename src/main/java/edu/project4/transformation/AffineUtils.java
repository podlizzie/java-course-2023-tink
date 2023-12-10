package edu.project4.transformation;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class AffineUtils {
    private AffineUtils() {

    }

    private static final int MAX_COLOR = 255;
    private static final int MAX_ATTEMPTS = 1000;

    public static List<AffineGenerator> getListOfAffineTransformations(int n) {
        List<AffineGenerator> transformations = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            transformations.add(generateValidCoefficients());
        }
        return transformations;
    }

    private static AffineGenerator generateValidCoefficients() {
        double a;
        double b;
        double c;
        double d;
        double e;
        double f;
        do {
            a = generateValidCoefficient();
            d = generateValidCoefficient();
            b = generateValidCoefficient();
            e = generateValidCoefficient();
            c = generateValidCoefficient();
            f = generateValidCoefficient();
        } while ((a * a + b * b + d * d + e * e >= 1 + Math.pow(a * e - b * d, 2)) && (b * b + e * e >= 1)
            && (a * a + d * d >= 1));

        int red = ThreadLocalRandom.current().nextInt(0, MAX_COLOR + 1);
        int green = ThreadLocalRandom.current().nextInt(0, MAX_COLOR + 1);
        int blue = ThreadLocalRandom.current().nextInt(0, MAX_COLOR + 1);

        Color color = new Color(red, green, blue);
        return new AffineGenerator(a, b, c, d, e, f, color);
    }

    private static double generateValidCoefficient() {
        int attempts = 0;
        double coefficient;
        do {
            attempts++;
            coefficient = ThreadLocalRandom.current().nextDouble(-1, 1);
        } while (attempts < MAX_ATTEMPTS);
        return coefficient;
    }
}
