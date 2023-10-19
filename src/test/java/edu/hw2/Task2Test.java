package edu.hw2;

import edu.hw2.task2.Rectangle;
import edu.hw2.task2.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {

    @Test
    @DisplayName("Test rectangle area")
    public void testRectangleArea() {
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(5.0);
        rectangle.setHeight(4.0);

        double expectedArea = 5.0 * 4.0;

        assertThat(rectangle.area()).isEqualTo(expectedArea);
        assertThat(rectangle.getWidth()).isEqualTo(5.0);
        assertThat(rectangle.getHeight()).isEqualTo(4.0);
    }

    @Test
    @DisplayName("Test square area")
    public void testSquareArea() {
        Square square = new Square();
        square.setHeight(5.0);
        square.setWeight(5.0);

        double expectedArea = 5.0 * 5.0;

        assertThat(square.area()).isEqualTo(expectedArea);
        assertThat(square.getWidth()).isEqualTo(5.0);
        assertThat(square.getHeight()).isEqualTo(5.0);
    }
}
