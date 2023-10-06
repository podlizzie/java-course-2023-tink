package edu.hw1;

public class Task2 {

    private Task2() {
    }

    @SuppressWarnings("MagicNumber")
    public static int countDigits(Number number) {
        Number num = number;
        if (num.equals(0)) {
            return 1;
        } else if (num.doubleValue() < 0) {
            num = Math.abs(num.doubleValue());
        }

        int count = 0;
        long firstPart = num.longValue();
        double secondPart = num.doubleValue() - firstPart;

        if (secondPart != 0) {
            return -1;
        }

        while (firstPart != 0) {
            firstPart /= 10;
            count++;
        }

        return count;
    }
}
