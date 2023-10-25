package edu.hw3;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {
    @ParameterizedTest
    @CsvSource({
        "1, I",
        "2, II",
        "3, III",
        "4, IV",
        "5, V",
        "9, IX",
        "10, X",
        "11, XI",
        "40, XL",
        "50, L",
        "91, XCI",
        "102, CII",
        "991, CMXCI",
        "1000, M",
        "3999, MMMCMXCIX"
    })
    @DisplayName("Test that convertToRoman returns correct result for valid input")
    public void testThatConvertToRomanReturnedCorrectResultForValidInput(int arabicNumber, String romanNumber) {
        String romanNum = Task4.convertToRoman(arabicNumber);
        Assertions.assertThat(romanNum).isEqualTo(romanNumber);
    }

    @ParameterizedTest
    @CsvSource({
        "-1, Input number should be in range [1; 3999]",
        "4000, Input number should be in range [1; 3999]"
    })
    @DisplayName("Test that convertToRoman throws IllegalArgumentException for invalid input")
    public void testThatConvertToRomanThrowsIllegalArgumentExceptionForInvalidInput(int input, String errorMessage) {
        try {
            Task4.convertToRoman(input);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isEqualTo(errorMessage);
        }
    }
}
