package edu.project1.states;

import org.jetbrains.annotations.NotNull;

public sealed interface GuessResult permits Defeat, Win, SuccessfulGuess, FailedGuess {
    char[] state();

    @NotNull String message();
}
