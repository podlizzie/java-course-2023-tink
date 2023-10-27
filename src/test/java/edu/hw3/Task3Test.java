package edu.hw3;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {
    @Test
    @DisplayName("Test that freqDict returns correct result for string list 1")
    public void testThatFreqDictReturnedCorrectResultForStringList1() {
        List<String> input = List.of("a", "bb", "a", "bb");
        Map<String, Integer> expected = Map.of("bb", 2, "a", 2);

        Map<String, Integer> result = Task3.freqDict(input);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Test that freqDict returns correct result for string list 2")
    public void testThatFreqDictReturnedCorrectResultForStringList2() {
        List<String> input = List.of("this", "and", "that", "and");
        Map<String, Integer> expected = Map.of("that", 1, "and", 2, "this", 1);

        Map<String, Integer> result = Task3.freqDict(input);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Test that freqDict returns correct result for string list with non-English string")
    public void testThatFreqDictReturnedCorrectResultForStringListNonEnglish() {
        List<String> input = List.of("код", "код", "код", "bug");
        Map<String, Integer> expected = Map.of("код", 3, "bug", 1);

        Map<String, Integer> result = Task3.freqDict(input);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Test that freqDict returns correct result for integer list")
    public void testThatFreqDictReturnedCorrectResultForIntegerList() {
        List<Integer> input = List.of(1, 1, 2, 2);
        Map<Integer, Integer> expected = Map.of(1, 2, 2, 2);

        Map<Integer, Integer> result = Task3.freqDict(input);
        assertThat(result).isEqualTo(expected);
    }
}
