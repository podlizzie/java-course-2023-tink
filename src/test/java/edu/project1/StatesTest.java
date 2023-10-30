package edu.project1;

import edu.project1.game.Session;
import edu.project1.states.Defeat;
import edu.project1.states.FailedGuess;
import edu.project1.states.GuessResult;
import edu.project1.states.SuccessfulGuess;
import edu.project1.states.Win;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StatesTest {

    @Test
    @DisplayName("Test correct guess state change - Success")
    void testCorrectGuessStateChangeSuccess() {
        String answer = "hangman";
        Session session = new Session(answer, 5);

        GuessResult result = session.guess('h');
        assertTrue(result instanceof SuccessfulGuess);
        assertEquals("h******", new String(result.state()));

        result = session.guess('a');
        assertTrue(result instanceof SuccessfulGuess);
        assertEquals("ha***a*", new String(result.state()));
    }

    @Test
    @DisplayName("Test correct guess state change - Failed and Defeat")
    void testIncorrectGuessStateChangeFailedAndDefeat() {
        String answer = "hangman";
        Session session = new Session(answer, 5);

        GuessResult result = session.guess('x');
        assertTrue(result instanceof FailedGuess);
        assertEquals("*******", new String(result.state()));

        result = session.guess('y');
        assertTrue(result instanceof FailedGuess);
        assertEquals("*******", new String(result.state()));

        result = session.guess('z');
        assertTrue(result instanceof FailedGuess);
        assertEquals("*******", new String(result.state()));

        result = session.guess('p');
        assertTrue(result instanceof FailedGuess);
        assertEquals("*******", new String(result.state()));

        result = session.guess('w');
        assertTrue(result instanceof FailedGuess);
        assertEquals("*******", new String(result.state()));

        result = session.guess('w');
        assertTrue(result instanceof Defeat);
        assertEquals("*******", new String(result.state()));
    }

    @Test
    @DisplayName("Test correct guess state change - Win")
    void testCorrectGuessStateChangeWin() {
        String answer = "zoo";
        Session session = new Session(answer, 5);

        GuessResult result = session.guess('z');
        assertTrue(result instanceof SuccessfulGuess);
        assertEquals("z**", new String(result.state()));

        result = session.guess('o');
        assertTrue(result instanceof Win);
        assertEquals("zoo", new String(result.state()));
    }

}
