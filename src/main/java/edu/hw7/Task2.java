package edu.hw7;

import java.util.stream.LongStream;

public class Task2 {
    private Task2() {

    }

    /**
     * Calculates the factorial of a given number.
     *
     * @param n the number for which the factorial is to be calculated
     * @return the factorial of the number n
     */
    public static long calculateFactorial(int n) {
        if (n < 0) {
            return -1;
        }

        return LongStream
            .rangeClosed(1, n)
            .parallel()
            .reduce((x, y) -> x * y)
            .orElse(1);
    }
}
