package edu.hw2;

import edu.hw2.task1.Addition;
import edu.hw2.task1.Constant;
import edu.hw2.task1.Exponent;
import edu.hw2.task1.Multiplication;
import edu.hw2.task1.Negate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    @DisplayName("Test constant")
    void testConstant() {
        var two = new Constant(2);
        assertThat(two.evaluate()).isEqualTo(2.0);
    }

    @Test
    @DisplayName("Test negate")
    void testNegate() {
        var negOne = new Negate(new Constant(1));
        assertThat(negOne.evaluate()).isEqualTo(-1.0);
    }

    @Test
    @DisplayName("Test addition")
    void testAddition() {
        var two = new Constant(2);
        var four = new Constant(4);
        var sumTwoFour = new Addition(two, four);
        assertThat(sumTwoFour.evaluate()).isEqualTo(6.0);
    }

    @Test
    @DisplayName("Test multiplication")
    void testMultiplication() {
        var two = new Constant(2);
        var four = new Constant(4);
        var mult = new Multiplication(two, four);
        assertThat(mult.evaluate()).isEqualTo(8.0);
    }

    @Test
    @DisplayName("Test exponent")
    void testExponent() {
        var two = new Constant(2);
        var exponent = new Exponent(two, 2);
        assertThat(exponent.evaluate()).isEqualTo(4.0);
    }

    @Test
    @DisplayName("Test complex expression")
    void testComplexExpression() {
        var two = new Constant(2);
        var four = new Constant(4);
        var negOne = new Negate(new Constant(1));
        var sumTwoFour = new Addition(two, four);
        var multiplication = new Multiplication(sumTwoFour, negOne);
        var exp = new Exponent(multiplication, 2);
        var res = new Addition(exp, new Constant(1));
        assertThat(res.evaluate()).isEqualTo(37.0);
    }

}
