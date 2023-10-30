package edu.project1.view;

import edu.project1.dictionary.HangmanDict;
import edu.project1.game.Session;
import edu.project1.states.Defeat;
import edu.project1.states.GuessResult;
import edu.project1.states.Win;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ConsoleHangman {
    private static final String BYE = "bye";
    private final static Logger LOGGER = LogManager.getLogger();
    private static final int MAX_ATTEMPTS = 5;
    private final HangmanDict dict = new HangmanDict();

    public ConsoleHangman() {
    }

    public void run() {

        Session session = new Session(dict.randomWord(), MAX_ATTEMPTS);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            LOGGER.info("Guess a letter: ");
            String input = scanner.next().toLowerCase();

            if (input.equals(BYE)) {
                LOGGER.info("See you soon!");
                break;
            } else if (input.length() > 1) {
                LOGGER.info("Please enter one letter.");
                continue;
            }

            GuessResult result = session.guess(input.charAt(0));
            printState(result);

            if (result instanceof Defeat || result instanceof Win) {
                break;
            }
        }
    }

    private void printState(GuessResult result) {
        LOGGER.info(result.message());
        LOGGER.info("The word: ");
        LOGGER.info(new String(result.state()));
    }
}
