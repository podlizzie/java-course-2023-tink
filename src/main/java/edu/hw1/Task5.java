package edu.hw1;

public final class Task5 {
    private static final int TEN = 10;

    private Task5() {

    }

    public static boolean isPalindromeDescendant(Integer num) {
        int descendant = Math.abs(num);

        if (isPalindrome(descendant) && descendant > TEN) {
            return true;
        } else if (String.valueOf(descendant).length() % 2 == 0) {
            return isPalindromeDescendant(makeDescendant(descendant));
        }
        return false;
    }

    private static boolean isPalindrome(int num) {
        int reverseNum = 0;
        int initialNum = num;
        while (num != 0) {
            int remainder = num % TEN;
            reverseNum = (reverseNum * TEN) + remainder;
            num /= TEN;
        }
        return initialNum == reverseNum;
    }

    private static int makeDescendant(Integer num) {
        String s = num.toString();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length() - 1; i += 2) {
            int sum = Character.getNumericValue(s.charAt(i)) + Character.getNumericValue(s.charAt(i + 1));
            sb.append(sum);
        }
        return Integer.parseInt(String.valueOf(sb));
    }
}
