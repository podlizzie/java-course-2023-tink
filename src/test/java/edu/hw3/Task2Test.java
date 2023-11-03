package edu.hw3;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class Task2Test {
    @Test
    @DisplayName("Test that clusterize returns correct result for simple input 1")
    public void testThatClusterizeReturnedCorrectResultForSimpleInput1() {
        List<String> result = Task2.clusterize("()()()");
        assertThat(result).containsExactly("()", "()", "()");
    }

    @Test
    @DisplayName("Test that clusterize returns correct result for simple input 2")
    public void testThatClusterizeReturnedCorrectResultForSimpleInput2() {
        List<String> result = Task2.clusterize("((()))");
        assertThat(result).containsExactly("((()))");
    }

    @Test
    @DisplayName("Test that clusterize returns correct result for simple input 3")
    public void testThatClusterizeReturnedCorrectResultForSimpleInput3() {
        List<String> result = Task2.clusterize("((()))(())()()(()())");
        assertThat(result).containsExactly("((()))", "(())", "()", "()", "(()())");
    }

    @Test
    @DisplayName("Test that clusterize returns correct result for simple input 4")
    public void testThatClusterizeReturnedCorrectResultForSimpleInput4() {
        List<String> result = Task2.clusterize("((())())(()(()()))");
        assertThat(result).containsExactly("((())())", "(()(()()))");
    }

    @Test
    @DisplayName("Test that clusterize returns empty list for input with different number of closing and opening brackets")
    public void testThatClusterizeReturnsEmptyListForInputWithDiffNumber() {
        List<String> result = Task2.clusterize("(((()))");
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Test that clusterize throws IllegalArgumentException for input with invalid characters")
    public void testThatClusterizeThrowsIllegalArgumentExceptionForInvalidCharacters() {
        try {
            Task2.clusterize("(abc())");
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isEqualTo("Input contains invalid characters");
        }

        try {
            Task2.clusterize("");
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isEqualTo("Input cannot be null or empty");
        }
    }
}
