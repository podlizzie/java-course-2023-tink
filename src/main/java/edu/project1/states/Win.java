package edu.project1.states;

import org.jetbrains.annotations.NotNull;

public record Win(char[] state) implements GuessResult {
    @Override
    public @NotNull String message() {
        return "You won!";
    }
}
