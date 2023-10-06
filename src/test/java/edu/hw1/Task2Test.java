package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    @DisplayName("Test with negative decimal number")
    void testNegativeDecimalNumber() {
        double number = -1.34;
        int result = Task2.countDigits(number);
        assertThat(result).isEqualTo(-1);
    }

    @Test
    @DisplayName("Test with positive decimal number")
    void testPositiveDecimalNumber() {
        double number = 1.34;
        int result = Task2.countDigits(number);
        assertThat(result).isEqualTo(-1);
    }

    @Test
    @DisplayName("Test with zero")
    void testZero() {
        int number = 0;
        int result = Task2.countDigits(number);
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("Test with positive integer")
    void testPositiveInteger() {
        int number = 6289;
        int result = Task2.countDigits(number);
        assertThat(result).isEqualTo(4);
    }

    @Test
    @DisplayName("Test with zero with leading zeros")
    void testZeroWithLeadingZeros() {
        int number = 0000;
        int result = Task2.countDigits(number);
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("Test with negative integer")
    void testNegativeInteger() {
        int number = -200;
        int result = Task2.countDigits(number);
        assertThat(result).isEqualTo(3);
    }

    @Test
    @DisplayName("Test with negative long number")
    void testNegativeLong() {
        long number = -3223244432323234L;
        int result = Task2.countDigits(number);
        assertThat(result).isEqualTo(16);
    }

    @Test
    @DisplayName("Test with positive long number")
    void testPositiveLong() {
        long number = 3223232323234L;
        int result = Task2.countDigits(number);
        assertThat(result).isEqualTo(13);
    }
}
