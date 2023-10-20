package edu.hw3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task3 {
    private Task3() {

    }

    public static <T> Map<T, Integer> freqDict(List<T> input) {
        Map<T, Integer> dict = new HashMap<>();

        for (T item : input) {
            if (dict.containsKey(item)) {
                dict.put(item, dict.get(item) + 1);
            } else {
                dict.put(item, 1);
            }
        }

        return dict;
    }
}
