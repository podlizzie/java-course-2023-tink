package edu.hw8.task2;

public class CalculatorFibonacci {
    private CalculatorFibonacci() {

    }

    public static long calculateFibonacci(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }

        return calculateFibonacci(n - 1) + calculateFibonacci(n - 2);
    }
}
