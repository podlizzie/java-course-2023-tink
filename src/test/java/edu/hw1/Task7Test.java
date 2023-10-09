package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {

    @Test
    @DisplayName("Test rotateRight function with positive shift")
    void testRotateRight1() {
        int result = Task7.rotateRight(8, 1);
        assertThat(result).isEqualTo(4);
    }

    @Test
    @DisplayName("Test rotateLeft function with positive shift")
    void testRotateLeft1() {
        int result = Task7.rotateLeft(17, 2);
        assertThat(result).isEqualTo(6);
    }

    @Test
    @DisplayName("Test rotateRight function with shift greater than length of binary number")
    void testRotateRightShiftGreaterThanLength() {
        int result = Task7.rotateRight(10, 5);
        assertThat(result).isEqualTo(5);
    }

    @Test
    @DisplayName("Test rotateLeft function with positive shift")
    void testRotateLeft2() {
        int result = Task7.rotateLeft(16, 1);
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("Test rotateLeft function with shift greater than length of binary number")
    void testRotateLeftShiftGreaterThanLength() {
        int result = Task7.rotateLeft(10, 5);
        assertThat(result).isEqualTo(5);
    }

    @Test
    @DisplayName("Test rotateLeft with zero shift")
    void testRotateLeftWithZeroShift() {
        int result = Task7.rotateLeft(10, 0);
        assertThat(result).isEqualTo(10);
    }

    @Test
    @DisplayName("Test rotateRight with zero shift")
    void testRotateRightWithZeroShift() {
        int result = Task7.rotateRight(10, 0);
        assertThat(result).isEqualTo(10);
    }

    @Test
    @DisplayName("Test rotateRight with (1111) number")
    void testRotateRightWithConstantNumberAfterShift() {
        int result = Task7.rotateRight(15, 10);
        assertThat(result).isEqualTo(15);
    }

    @Test
    @DisplayName("Test rotateRight with negative shift")
    void testRotateRightWithNegativelyShift() {
        int result = Task7.rotateRight(10, -3);
        assertThat(result).isEqualTo(-1);
    }

    @Test
    @DisplayName("Test rotateLeft with negative shift")
    void testRotateLeftWithNegativelyShift() {
        int result = Task7.rotateLeft(10, -3);
        assertThat(result).isEqualTo(-1);
    }

}
