package edu.hw3;

import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task3Test {
    @Test
    @DisplayName("Test with list of strings #1")
    public void testWithStringList1() {
        List<String> input = List.of("a", "bb", "a", "bb");
        Map<String, Integer> expected = Map.of("bb", 2, "a", 2);

        Map<String, Integer> result = Task3.freqDict(input);
        Assertions.assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Test with list of strings #2")
    public void testWithStringListW2() {
        List<String> input = List.of("this", "and", "that", "and");
        Map<String, Integer> expected = Map.of("that", 1, "and", 2, "this", 1);

        Map<String, Integer> result = Task3.freqDict(input);
        Assertions.assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Test with list of strings with non-English string")
    public void testWithStringListNonEnglish() {
        List<String> input = List.of("код", "код", "код", "bug");
        Map<String, Integer> expected = Map.of("код", 3, "bug", 1);

        Map<String, Integer> result = Task3.freqDict(input);
        Assertions.assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Test with list of integers")
    public void testWithIntegerList() {
        List<Integer> input = List.of(1, 1, 2, 2);
        Map<Integer, Integer> expected = Map.of(1, 2, 2, 2);

        Map<Integer, Integer> result = Task3.freqDict(input);
        Assertions.assertThat(result).isEqualTo(expected);
    }
}
