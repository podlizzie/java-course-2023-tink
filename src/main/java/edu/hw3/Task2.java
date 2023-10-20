package edu.hw3;

import java.util.ArrayList;
import java.util.List;

public class Task2 {
    private Task2() {

    }

    public static List<String> clusterize(String input) {
        List<String> output = new ArrayList<>();
        int count = 0;
        int start = 0;

        for (int i = 0; i < input.length(); i++) {
            char symbol = input.charAt(i);
            if (symbol == '(') {
                count++;
            } else if (symbol == ')') {
                count--;
                if (count == 0) {
                    output.add(input.substring(start, i + 1));
                    start = i + 1;
                }
            }
        }
        return output;
    }

}
