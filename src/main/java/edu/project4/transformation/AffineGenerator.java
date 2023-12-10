package edu.project4.transformation;

import edu.project4.entity.Point;
import java.awt.Color;
import java.util.Random;

public class AffineGenerator implements Transformation {
    Random random = new Random();
    private double a;
    private double b;
    private double c;
    private double d;
    private double e;
    private double f;
    private Color color;

    public AffineGenerator(Color color) {
        this.color = color;
        generateValidCoefficients();
    }

    private double generateValidCoefficient() {
        return random.nextDouble() * 2 - 1;
    }

    private void generateValidCoefficients() {
        do {
            a = generateValidCoefficient();
            d = generateValidCoefficient();
        } while (a * a + d * d >= 1);

        do {
            b = generateValidCoefficient();
            e = generateValidCoefficient();
        } while (b * b + e * e >= 1);

        do {
            c = generateValidCoefficient();
            f = generateValidCoefficient();
        } while (a * a + b * b + d * d + e * e >= 1 + Math.pow(a * e - b * d, 2));
    }

    @Override
    public Point apply(Point point) {
        double newX = a * point.x() + b * point.y() + c;
        double newY = d * point.x() + e * point.y() + f;

        return new Point(newX, newY);
    }

    public Color getColor() {
        return color;
    }
}
