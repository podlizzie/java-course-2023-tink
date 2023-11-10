package edu.hw5;

import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;

public class Task6 {

    private Task6() {

    }

    public static Boolean isSubsequence(@NotNull String allStr, @NotNull String maybeSubsequence) {
        return allStr.matches(".*" + Pattern.quote(maybeSubsequence) + ".*");
    }
}
