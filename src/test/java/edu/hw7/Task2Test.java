package edu.hw7;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {

    @Test
    public void testThatFactorialOf5Returned120() {
        // given
        int number = 5;

        // when
        long result = Task2.calculateFactorial(number);

        // then
        assertThat(result).isEqualTo(120);
    }

    @Test
    public void testThatFactorialOf10Returned3628800() {
        // given
        int number = 10;

        // when
        long result = Task2.calculateFactorial(number);

        // then
        assertThat(result).isEqualTo(3628800);
    }

    @Test
    public void testThatFactorialOf1Returned1() {
        // given
        int number = 1;

        // when
        long result = Task2.calculateFactorial(number);

        // then
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void testThatFactorialOf0Returned1() {
        // given
        int number = 0;

        // when
        long result = Task2.calculateFactorial(number);

        // then
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void testThatFactorialWithNegativeReturnedNegative1() {
        // given
        int number = -10;

        // when
        long result = Task2.calculateFactorial(number);

        // then
        assertThat(result).isEqualTo(-1);
    }

}

