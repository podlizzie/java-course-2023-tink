package edu.project1.game;

import edu.project1.states.Defeat;
import edu.project1.states.FailedGuess;
import edu.project1.states.GuessResult;
import edu.project1.states.SuccessfulGuess;
import edu.project1.states.Win;
import org.jetbrains.annotations.NotNull;

public class Session {
    private final String answer;
    private final char[] hiddenWord;
    private final int maxAttempts;
    private int attempts;
    private static final char HIDDEN_LETTER = '*';

    public Session(String answer, int maxAttempts) {
        this.answer = answer.toLowerCase();
        this.maxAttempts = maxAttempts;
        this.attempts = 0;
        this.hiddenWord = new char[answer.length()];
        for (int i = 0; i < answer.length(); i++) {
            this.hiddenWord[i] = HIDDEN_LETTER;
        }
    }

    @NotNull
    public GuessResult guess(char guess) {
        if (attempts == maxAttempts) {
            return new Defeat(hiddenWord);
        }
        boolean isMiss = true;
        for (int i = 0; i < answer.length(); i++) {
            if (answer.charAt(i) == guess) {
                hiddenWord[i] = guess;
                isMiss = false;
            }
        }
        if (isMiss) {
            attempts++;
            return new FailedGuess(hiddenWord, attempts, maxAttempts);
        }
        if (answer.equals(new String(hiddenWord))) {
            return new Win(hiddenWord);
        }
        return new SuccessfulGuess(hiddenWord);
    }

}
