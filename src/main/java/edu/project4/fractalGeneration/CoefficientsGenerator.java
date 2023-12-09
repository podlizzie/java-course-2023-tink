package edu.project4.fractalGeneration;

import java.util.Random;

public class CoefficientsGenerator {
    private final Random random;
    private double a;
    private double b;
    private double c;
    private double d;
    private double e;
    private double f;

    public CoefficientsGenerator() {
        this.random = new Random();
        generateValidCoefficients();
    }

    public CoefficientsGenerator(long seed) {
        this.random = new Random(seed);
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

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    public double getD() {
        return d;
    }

    public double getE() {
        return e;
    }

    public double getF() {
        return f;
    }
}
