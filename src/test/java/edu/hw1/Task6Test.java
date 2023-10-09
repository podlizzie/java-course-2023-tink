package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task6Test {
    @Test
    @DisplayName("Test with input 3524")
    public void testCountK3524() {
        assertThat(Task6.countK(3524)).isEqualTo(3);
    }

    @Test
    @DisplayName("Test with input 6621")
    public void testCountK6621() {
        assertThat(Task6.countK(6621)).isEqualTo(5);
    }

    @Test
    @DisplayName("Test with input 6554")
    public void testCountK6554() {
        assertThat(Task6.countK(6554)).isEqualTo(4);
    }

    @Test
    @DisplayName("Test with input 1234")
    public void testCountK1234() {
        assertThat(Task6.countK(1234)).isEqualTo(3);
    }

    @Test
    @DisplayName("Test with small number")
    public void testCountK345() {
        assertThat(Task6.countK(345)).isEqualTo(-1);
    }

    @Test
    @DisplayName("Test with big number")
    public void testCountK345555() {
        assertThat(Task6.countK(345555)).isEqualTo(-1);
    }

    @Test
    @DisplayName("Test with same numbers")
    public void testCountK5555() {
        assertThat(Task6.countK(5555)).isEqualTo(-1);
    }

    @Test
    @DisplayName("Test with Kaprekar number")
    public void testCountK6174() {
        assertThat(Task6.countK(6174)).isEqualTo(0);
    }

    @Test
    @DisplayName("Test with adding zero to the top")
    public void testCountK1222() {
        assertThat(Task6.countK(1222)).isEqualTo(5);
    }
}
