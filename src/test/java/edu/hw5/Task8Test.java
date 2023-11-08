package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task8Test {
    @ParameterizedTest
    @ValueSource(strings = {
        "0",
        "010",
        "000",
        "11010",
        "1"
    })
    public void testThatIsOddLengthReturnedTrue(String input) {
        assertThat(Task8.isOddString(input)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "00",
        "0000",
        "1111",
        "1101010101",
        "10"
    })
    public void testThatIsOddLengthReturnedFalse(String input) {
        assertThat(Task8.isOddString(input)).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "000",
        "000000",
        "000000000",
    })
    public void testThatIsMultipleOf3ZerosReturnedTrue(String input) {
        assertThat(Task8.IsMultipleOf3Zeros(input)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "00",
        "0",
        "10000",
        "010000"
    })
    public void testThatIsMultipleOf3ZerosReturnedFalse(String input) {
        assertThat(Task8.IsMultipleOf3Zeros(input)).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "0",
        "1",
        "00",
        "1010",
        "10000",
        "00010"
    })
    public void testThatIsNot11Or111ReturnedTrue(String input) {
        assertThat(Task8.IsNot11Or111(input)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "11",
        "111",
        "11111",
        "10111",
        "1110111"
    })
    public void testThatIsNot11Or111ReturnedFalse(String input) {
        assertThat(Task8.IsNot11Or111(input)).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "1",
        "101",
        "1010",
        "10101"
    })
    public void testThatIsOddPositionIs1ReturnedTrue(String input) {
        assertThat(Task8.IsOddPositionIs1(input)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "0",
        "00",
        "010",
        "0100",
        "000000"
    })
    public void testThatIsOddPositionIs1ReturnedFalse(String input) {
        assertThat(Task8.IsOddPositionIs1(input)).isFalse();
    }
}
