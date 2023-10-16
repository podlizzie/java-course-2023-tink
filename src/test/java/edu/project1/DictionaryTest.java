package edu.project1;

import edu.project1.dictionary.HangmanDict;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DictionaryTest {

    @Test
    @DisplayName("Test Random Word")
    void testRandomWord() {
        List<String> words =
            Arrays.asList("apple", "juice", "math", "notebook", "sandwich", "shrimp", "table", "malware", "  j");
        HangmanDict dictionary = new HangmanDict();

        String randomWord = dictionary.randomWord();

        assertThat(randomWord).isIn(words);
    }

    @Test
    @DisplayName("Test random word not blank")
    void testRandomWordNotBlank() {
        HangmanDict hangmanDict = new HangmanDict();
        String randomWord = hangmanDict.randomWord();
        assertThat(randomWord).isNotBlank();
    }

}
