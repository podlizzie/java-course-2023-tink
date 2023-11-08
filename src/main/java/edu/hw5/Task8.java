package edu.hw5;

import org.jetbrains.annotations.NotNull;

public class Task8 {
    private static final String ODD_LENGHT_STRING_PATTERN = "^([01]{2})*[01]$";
    private static final String NOT_11_OR_111_PATTERN = "^(?!.*11|.*111)[01]*$";
    private static final String COUNT_OF_0_MULTIPLY_3_PATTERN = "^((1*01*){3})+$";
    private static final String ODD_POSITION_IS_1_PATTERN = "^(1[0|1]*)+$";

    private Task8() {

    }

    public static Boolean isOddString(@NotNull String str) {
        return str.matches(ODD_LENGHT_STRING_PATTERN);
    }

    public static Boolean IsNot11Or111(@NotNull String str) {
        return str.matches(NOT_11_OR_111_PATTERN);
    }

    public static Boolean IsMultipleOf3Zeros(@NotNull String str) {
        return str.matches(COUNT_OF_0_MULTIPLY_3_PATTERN);
    }

    public static Boolean IsOddPositionIs1(@NotNull String str) {
        return str.matches(ODD_POSITION_IS_1_PATTERN);
    }
}
