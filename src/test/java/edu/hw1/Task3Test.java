package edu.hw1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {

    @Test
    @DisplayName("Test with first array nested in second")
    void testIsNestable_FirstArrayNestedInSecond() {
        int[] a1 = {1, 2, 3, 4};
        int[] a2 = {0, 6};
        assertThat(Task3.isNestable(a1, a2)).isTrue();
    }

    @Test
    @DisplayName("Test with second array nested in first")
    void testIsNestable_SecondArrayNestedInFirst() {
        int[] a1 = {3, 1};
        int[] a2 = {4, 0};
        assertThat(Task3.isNestable(a1, a2)).isTrue();
    }

    @Test
    @DisplayName("Test with empty first array")
    void testIsNestable_EmptyFirstArray() {
        int[] a1 = {};
        int[] a2 = {0, 6};
        assertThat(Task3.isNestable(a1, a2)).isFalse();
    }

    @Test
    @DisplayName("Test with empty second array")
    void testIsNestable_EmptySecondArray() {
        int[] a1 = {3, 1};
        int[] a2 = {};
        assertThat(Task3.isNestable(a1, a2)).isFalse();
    }

    @Test
    @DisplayName("Test with empty all arrays")
    void testIsNestable_EmptyAllArrays() {
        int[] a1 = {};
        int[] a2 = {};
        assertThat(Task3.isNestable(a1, a2)).isFalse();
    }

    @Test
    @DisplayName("Test with the same min and max")
    void testIsNestable_FirstArrayNotNestedInSecond() {
        int[] a1 = {9, 9, 8};
        int[] a2 = {8, 9};
        assertThat(Task3.isNestable(a1, a2)).isFalse();
    }

    @Test
    @DisplayName("Test with first array not nested in second")
    void testIsNestable_SecondArrayNotNestedInFirst() {
        int[] a1 = {1, 2, 3, 4};
        int[] a2 = {2, 3};
        assertThat(Task3.isNestable(a1, a2)).isFalse();
    }

    @Test
    @DisplayName("Test with null first array")
    void testIsNestable_NullFirstArray() {
        int[] a1 = null;
        int[] a2 = {1, 2, 3, 4};
        assertThat(Task3.isNestable(a1, a2)).isFalse();
    }

    @Test
    @DisplayName("Test with null second array")
    void testIsNestable_NullSecondArray() {
        int[] a1 = {1, 2, 3, 4};
        int[] a2 = null;
        assertThat(Task3.isNestable(a1, a2)).isFalse();
    }
}
