package edu.project1.states;

import org.jetbrains.annotations.NotNull;

public record SuccessfulGuess(char[] state) implements GuessResult {
    @Override
    public @NotNull String message() {
        return "Hit!";
    }
}
