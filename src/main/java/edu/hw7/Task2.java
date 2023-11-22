package edu.hw7;

import java.util.stream.IntStream;

public class Task2 {
    private Task2() {

    }

    /**
     * Calculates the factorial of a given number.
     *
     * @param n the number for which the factorial is to be calculated
     * @return the factorial of the number n
     */
    public static int calculateFactorial(int n) {
        return IntStream
            .rangeClosed(1, n)
            .parallel()
            .reduce((x, y) -> x * y)
            .orElse(1);
    }
}
