package edu.project1.states;

import org.jetbrains.annotations.NotNull;

public record FailedGuess(char[] state, int attempt, int maxAttempts) implements GuessResult {
    @Override
    public @NotNull String message() {
        return "Missed, mistake " + attempt + " out of " + maxAttempts + ".";
    }
}
