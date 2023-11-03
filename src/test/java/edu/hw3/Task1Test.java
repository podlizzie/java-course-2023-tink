package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    @DisplayName("Test that atbashCode returns correct result with lowercase input")
    public void testThatAtbashCodeReturnsCorrectResultWithLowerCaseInput() {
        String input = "hello world";
        String expectedOutput = "svool dliow";

        String result = Task1.atbashCode(input);

        assertThat(result).isEqualTo(expectedOutput);
    }

    @Test
    @DisplayName("Test that atbashCode returns correct result with russian lowercase input")
    public void testThatAtbashCodeReturnsCorrectResultWithRussianLowerCaseInput() {
        String input = "Привет";
        String expectedOutput = "Привет";

        String result = Task1.atbashCode(input);

        assertThat(result).isEqualTo(expectedOutput);
    }

    @Test
    @DisplayName("Test that atbashCode returns correct result with uppercase input")
    public void testThatAtbashCodeReturnsCorrectResultWithUpperCaseInput() {
        String input = "HELLO WORLD";
        String expectedOutput = "SVOOL DLIOW";

        String result = Task1.atbashCode(input);

        assertThat(result).isEqualTo(expectedOutput);
    }

    @Test
    @DisplayName("Test that atbashCode returns correct result with mixed letters input")
    public void testThatAtbashCodeReturnsCorrectResultWithMixedLettersInput() {
        String input = "Hello World";
        String expectedOutput = "Svool Dliow";

        String result = Task1.atbashCode(input);

        assertThat(result).isEqualTo(expectedOutput);
    }

    @Test
    @DisplayName("Test that atbashCode returns the input string without letters")
    public void testThatAtbashCodeReturnsInputWithoutLetters() {
        String input = "12345&*^$#";
        String expectedOutput = "12345&*^$#";

        String result = Task1.atbashCode(input);

        assertThat(result).isEqualTo(expectedOutput);
    }

    @Test
    @DisplayName("Test that atbashCode returns correct result with mixed string")
    public void testThatAtbashCodeReturnsCorrectResultWithMixedString() {
        String input = "Let me guess, you’re checking hw3!";
        String expectedOutput = "Ovg nv tfvhh, blf’iv xsvxprmt sd3!";

        String result = Task1.atbashCode(input);

        assertThat(result).isEqualTo(expectedOutput);
    }

    @Test
    @DisplayName("Test that atbashCode returns correct result with mixed big string")
    public void testThatAtbashCodeReturnsCorrectResultWithMixedBigString() {
        String input =
            "Any fool can write code that a computer can understand. Good programmers write code that humans can understand. ― Martin Fowler";
        String expectedOutput =
            "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi";

        String result = Task1.atbashCode(input);

        assertThat(result).isEqualTo(expectedOutput);
    }
}
