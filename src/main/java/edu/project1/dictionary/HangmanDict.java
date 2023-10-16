package edu.project1.dictionary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public class HangmanDict implements Dictionary {

    private static final Logger LOGGER = LogManager.getLogger();

    public HangmanDict() {
    }

    @Override
    @NotNull
    public List<String> dict() {
        List<String> words = new ArrayList<>();
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("dictionary.txt");

            assert inputStream != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                if (isValidWord(line)) {
                    words.add(line);
                } else {
                    LOGGER.warn("Invalid word found in dictionary: {}", line);
                }
            }
            reader.close();
            inputStream.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return words;
    }

    @NotNull
    @Override
    public String randomWord() {
        Random random = new Random();
        List<String> dict = dict();
        int randomNum = random.nextInt(dict.size());
        return dict.get(randomNum);
    }

    private boolean isValidWord(String word) {
        return word.trim().length() > 1;
    }
}
