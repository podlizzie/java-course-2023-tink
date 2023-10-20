package edu.hw3;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class Task2Test {
    @Test
    @DisplayName("Simple test 1")
    public void testClusterize1() {
        List<String> result = Task2.clusterize("()()()");
        assertThat(result).containsExactly("()", "()", "()");
    }

    @Test
    @DisplayName("Simple test 2")
    public void testClusterize2() {
        List<String> result = Task2.clusterize("((()))");
        assertThat(result).containsExactly("((()))");
    }

    @Test
    @DisplayName("Simple test 3")
    public void testClusterize3() {
        List<String> result = Task2.clusterize("((()))(())()()(()())");
        assertThat(result).containsExactly("((()))", "(())", "()", "()", "(()())");
    }

    @Test
    @DisplayName("Simple test 4")
    public void testClusterize4() {
        List<String> result = Task2.clusterize("((())())(()(()()))");
        assertThat(result).containsExactly("((())())", "(()(()()))");
    }

    @Test
    @DisplayName("Test with different number of closing and opening brackets")
    public void testWithDiffNumber() {
        List<String> result = Task2.clusterize("(((()))");
        assertThat(result).isEmpty();
    }
}
