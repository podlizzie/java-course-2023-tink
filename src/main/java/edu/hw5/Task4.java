package edu.hw5;

import org.jetbrains.annotations.NotNull;

public class Task4 {

    private static final String REGEX_PATTERN = ".*[~!@#$%^&*|].*";

    private Task4() {

    }

    public static Boolean passwordValidation(@NotNull String password) {
        return password.matches(REGEX_PATTERN);
    }
}
