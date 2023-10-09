package edu.hw1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task1Test {

    @Test
    @DisplayName("Test with 1 minute")
    void testWith1Minute() {
        assertEquals(60, Task1.minutesToSeconds("01:00"));
    }

    @Test
    @DisplayName("Test with 13 minutes and 56 seconds")
    void testWith13MinutesAnd56Seconds() {
        assertEquals(836, Task1.minutesToSeconds("13:56"));
    }

    @Test
    @DisplayName("Test with 1 part")
    void testWith1Part() {
        assertEquals(-1, Task1.minutesToSeconds("5:"));
    }

    @Test
    @DisplayName("Test with wrong seconds")
    void testWithInvalidSeconds() {
        assertEquals(-1, Task1.minutesToSeconds("10:60"));
    }

    @Test
    @DisplayName("Test with invalid format (with letters)")
    void testWithLetters() {
        assertEquals(-1, Task1.minutesToSeconds("no:number"));
    }

    @Test
    @DisplayName("Test with null argument")
    void testWithNullArgument() {
        assertEquals(-1, Task1.minutesToSeconds(null));
    }

    @Test
    @DisplayName("Test with many-many minutes")
    void testWithManyMinutes() {
        assertEquals(5999999999999999L, Task1.minutesToSeconds("99999999999999:59"));
    }

    @Test
    @DisplayName("Test with negative digits")
    void testWithNegativeNumber() {
        assertEquals(-1, Task1.minutesToSeconds("-1:-1"));
    }

    @Test
    @DisplayName("Test with ignorant zeroes")
    void testWithIgnorantZeroes() {
        assertEquals(735, Task1.minutesToSeconds("00012:0015"));
    }

    @Test
    @DisplayName("Test with spaces")
    void testWithSpaces() {
        assertEquals(735, Task1.minutesToSeconds("    12 :   15"));
    }
}
