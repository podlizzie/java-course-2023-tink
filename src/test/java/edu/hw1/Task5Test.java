package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {

    @Test
    @DisplayName("Test with palindrome numbers")
    void testWithPalindromeNumbers() {
        assertThat(Task5.isPalindromeDescendant(11)).isTrue();
        assertThat(Task5.isPalindromeDescendant(1331)).isTrue();
    }

    @Test
    @DisplayName("Test with numbers of length > 1")
    void testWithNumbersOfLengthBiggerThanOne() {
        assertThat(Task5.isPalindromeDescendant(11211230)).isTrue();
        assertThat(Task5.isPalindromeDescendant(13001120)).isTrue();
        assertThat(Task5.isPalindromeDescendant(23336014)).isTrue();
    }

    @Test
    @DisplayName("Test non-palindrome numbers and numbers of length <= 1")
    void testWithNonPalindromeNumbersAndNumbersOfLengthOneOrLess() {
        assertThat(Task5.isPalindromeDescendant(12345)).isFalse();
        assertThat(Task5.isPalindromeDescendant(6789)).isFalse();
        assertThat(Task5.isPalindromeDescendant(5)).isFalse();
    }

    @Test
    @DisplayName("Test with negative numbers")
    void testWithNegativeNumbers() {
        assertThat(Task5.isPalindromeDescendant(-363)).isTrue();
        assertThat(Task5.isPalindromeDescendant(-123456)).isFalse();
        assertThat(Task5.isPalindromeDescendant(-5)).isFalse();
    }
}
