package edu.hw3;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Task4 {
    private Task4() {

    }

    private static final int ILLEGAL_NUM = 3999;
    private static final int ILLEGAL_NUM_LOWER_BOUND = 0;

    @SuppressWarnings("MagicNumber")
    private static Map<Integer, String> createMap() {
        Map<Integer, String> arabToRoman = new LinkedHashMap<>();
        arabToRoman.put(1000, "M");
        arabToRoman.put(900, "CM");
        arabToRoman.put(500, "D");
        arabToRoman.put(400, "CD");
        arabToRoman.put(100, "C");
        arabToRoman.put(90, "XC");
        arabToRoman.put(50, "L");
        arabToRoman.put(40, "XL");
        arabToRoman.put(10, "X");
        arabToRoman.put(9, "IX");
        arabToRoman.put(5, "V");
        arabToRoman.put(4, "IV");
        arabToRoman.put(1, "I");
        return arabToRoman;
    }

    public static String convertToRoman(int input) {
        HashMap<Integer, String> arabToRoman = (HashMap<Integer, String>) createMap();
        if (input <= ILLEGAL_NUM_LOWER_BOUND || input > ILLEGAL_NUM) {
            throw new IllegalArgumentException("Input number should be in range [1; 3999]");
        }
        StringBuilder romanNum = new StringBuilder();
        int arabNum = input;
        for (Integer key : arabToRoman.keySet()) {
            while (arabNum >= key) {
                romanNum.append(arabToRoman.get(key));
                arabNum -= key;
            }
        }
        return romanNum.toString();
    }

}
