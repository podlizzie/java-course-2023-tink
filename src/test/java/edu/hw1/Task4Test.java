package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {
    @Test
    @DisplayName("Test with digits")
    void testWithDigits() {
        assertThat(Task4.fixString("123456")).isEqualTo("214365");
    }

    @Test
    @DisplayName("Test with sentence")
    void testWithSentence() {
        assertThat(Task4.fixString("hTsii  s aimex dpus rtni.g")).isEqualTo("This is a mixed up string.");
    }

    @Test
    @DisplayName("Test with 5 letters")
    void testWith5Letters() {
        assertThat(Task4.fixString("badce")).isEqualTo("abcde");
    }

    @Test
    @DisplayName("Test with single letter")
    void testWithSingleLetter() {
        assertThat(Task4.fixString("a")).isEqualTo("a");
    }

    @Test
    @DisplayName("Test with empty string")
    void testWithEmptyString() {
        assertThat(Task4.fixString("")).isEqualTo("");
    }

    @Test
    @DisplayName("Test with spaces")
    void testWithSpaces() {
        assertThat(Task4.fixString("  ")).isEqualTo("  ");
    }

    @Test
    @DisplayName("Test with repeat letters")
    void testWithRepeatLetters() {
        assertThat(Task4.fixString("LLLLiizzaaB")).isEqualTo("LLLLiizzaaB");
    }
}
