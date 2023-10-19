package edu.hw3;

public class Task1 {
    private Task1() {

    }

    public static String atbashCode(String input) {
        StringBuilder output = new StringBuilder();
        for (char letter : input.toCharArray()) {
            if (Character.isLetter(letter)) {
                char newChar;
                if (letter >= 'a' && letter <= 'z') {
                    newChar = (char) ('z' - (letter - 'a'));
                } else {
                    newChar = (char) ('Z' - (letter - 'A'));
                }
                output.append(newChar);
            } else {
                output.append(letter);
            }
        }
        return output.toString();
    }
}
