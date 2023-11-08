package edu.hw5;

import org.jetbrains.annotations.NotNull;

public class Task5 {
    private static final String REGEX_PATTERN = "[АВЕКМНОРСТУХ]{1}\\d{3}[АВЕКМНОРСТУХ]{2}\\d{2,3}";

    private Task5() {

    }

    public static Boolean carNumberValidation(@NotNull String number) {
        return number.matches(REGEX_PATTERN);
    }
}
