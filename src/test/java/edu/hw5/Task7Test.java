package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {
    @ParameterizedTest
    @ValueSource(strings = {
        "010",
        "110",
        "1000",
        "1100110"
    })
    public void testThatIsAtLeastThreeSymbolsThirdIs0ReturnedTrue(String input) {
        boolean result = Task7.isAtLeastThreeSymbolsThirdIs0(input);
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "0",
        "00",
        "0110",
        "111111110"
    })
    public void testThatIsAtLeastThreeSymbolsThirdIs0ReturnedFalse(String input) {
        boolean result = Task7.isAtLeastThreeSymbolsThirdIs0(input);
        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "0",
        "0100",
        "1101",
        "111111"
    })
    public void testThatIsStartsAndEndsWithOneSymbolReturnedTrue(String input) {
        boolean result = Task7.isStartsAndEndsWithOneSymbol(input);
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "",
        "10",
        "0101000001",
        "1010"
    })
    public void testThatIsStartsAndEndsWithOneSymbolReturnedFalse(String input) {
        boolean result = Task7.isStartsAndEndsWithOneSymbol(input);
        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "0",
        "00",
        "000",
        "101"
    })
    public void testThatIsLengthNoLessThan1NoMoreThan3ReturnedTrue(String input) {
        boolean result = Task7.isLengthNoLessThan1NoMoreThan3(input);
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "",
        "0000",
        "10101",
        "111111"
    })
    public void testThatIsLengthNoLessThan1NoMoreThan3ReturnedFalse(String input) {
        boolean result = Task7.isLengthNoLessThan1NoMoreThan3(input);
        assertThat(result).isFalse();
    }
}
