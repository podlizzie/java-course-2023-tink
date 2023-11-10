package edu.hw5;

import org.jetbrains.annotations.NotNull;

public class Task7 {
    private static final String AT_LEAST_THREE_SYMBOLS_THIRD_IS_0_PATTERN = "[0-1]{2}0{1}[0-1]*";
    private static final String START_AND_END_WITH_ONE_SYMBOL_PATTERN = "^(0[0-1]*0|1[0-1]*1|[0-1])$";
    private static final String LENGHT_NO_LESS_THAN_1_NO_MORE_THAN_3_PATTERN = "[0-1]{1,3}";

    private Task7() {

    }

    public static Boolean isAtLeastThreeSymbolsThirdIs0(@NotNull String str) {
        return str.matches(AT_LEAST_THREE_SYMBOLS_THIRD_IS_0_PATTERN);
    }

    public static Boolean isStartsAndEndsWithOneSymbol(@NotNull String str) {
        return str.matches(START_AND_END_WITH_ONE_SYMBOL_PATTERN);
    }

    public static Boolean isLengthNoLessThan1NoMoreThan3(@NotNull String str) {
        return str.matches(LENGHT_NO_LESS_THAN_1_NO_MORE_THAN_3_PATTERN);
    }
}
