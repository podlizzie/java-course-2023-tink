package edu.project1.states;

import org.jetbrains.annotations.NotNull;

public record Defeat(char[] state) implements GuessResult {
    @Override
    public @NotNull String message() {
        return "Sorry, you lost. Try again!";
    }
}
