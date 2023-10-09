package edu.hw1;

import java.util.Arrays;

public class Task6 {
    private static final int NUMBER_LENGHT = 4;

    private Task6() {

    }

    public static int countK(int number) {
        if (!checkNum(number)) {
            return -1;
        }
        return calculateCountK(String.valueOf(number));
    }

    private static int calculateCountK(String num) {
        if (num.equals("6174")) {
            return 0;
        }

        int greaterNum = Integer.parseInt(descendingNum(num));
        int lowerNum = Integer.parseInt(increaseNum(num));

        StringBuilder diff = new StringBuilder(String.valueOf(greaterNum - lowerNum));
        while (diff.length() < NUMBER_LENGHT) {
            diff.insert(0, "0");
        }
        return 1 + calculateCountK(String.valueOf(diff));
    }

    private static boolean checkNum(int number) {
        String strNum = String.valueOf(number);
        char firstDigit = strNum.charAt(0);
        if (strNum.length() == NUMBER_LENGHT) {
            for (int i = 1; i < strNum.length(); i++) {
                if (strNum.charAt(i) != firstDigit) {
                    return true;
                }
            }
        }
        return false;
    }

    private static String increaseNum(String num) {

        char[] sortedArray = num.toCharArray();
        Arrays.sort(sortedArray);

        return new String(sortedArray);

    }

    private static String descendingNum(String num) {

        char[] sortedArray = num.toCharArray();
        Arrays.sort(sortedArray);

        return new StringBuilder(new String(sortedArray)).reverse().toString();

    }
}
