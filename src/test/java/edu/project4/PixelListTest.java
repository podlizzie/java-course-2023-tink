package edu.project4;

import edu.project4.entity.Pixel;
import edu.project4.entity.PixelList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PixelListTest {

    @Test
    @DisplayName("Test that height is returned correctly")
    void testThatHeightReturnedCorrectly() {
        // given
        int expectedHeight = 10;
        PixelList pixelList = new PixelList(expectedHeight, 15, true, false);

        // when
        int actualHeight = pixelList.getHeight();

        // then
        assertThat(actualHeight).isEqualTo(expectedHeight);
    }

    @Test
    @DisplayName("Test that width is returned correctly")
    void testThatWidthReturnedCorrectly() {
        // given
        int expectedWidth = 20;
        PixelList pixelList = new PixelList(10, expectedWidth, false, true);

        // when
        int actualWidth = pixelList.getWidth();

        // then
        assertThat(actualWidth).isEqualTo(expectedWidth);
    }

    @Test
    @DisplayName("Test that symmetric pixels are reflected vertically")
    void testThatSymmetricPixelsReflectedVertically() {
        // given
        PixelList pixelList = new PixelList(2, 2, true, false);

        // when
        Pixel pixel00 = pixelList.getPixel(0, 0);
        Pixel pixel01 = pixelList.getPixel(0, 1);

        // then
        assertThat(pixel01).isSameAs(pixel00);
    }

    @Test
    @DisplayName("Test that symmetric pixels are reflected horizontally")
    void testThatSymmetricPixelsReflectedHorizontally() {
        // given
        PixelList pixelList = new PixelList(2, 2, false, true);

        // when
        Pixel pixel00 = pixelList.getPixel(0, 0);
        Pixel pixel10 = pixelList.getPixel(1, 0);

        // then
        assertThat(pixel10).isSameAs(pixel00);
    }

    @Test
    @DisplayName("Test that all pixels are symmetric")
    void testThatAllPixelsAreSymmetric() {
        // given
        PixelList pixelList = new PixelList(4, 4, true, true);

        // when
        Pixel pixel00 = pixelList.getPixel(0, 0);
        Pixel pixel03 = pixelList.getPixel(0, 3);
        Pixel pixel30 = pixelList.getPixel(3, 0);
        Pixel pixel33 = pixelList.getPixel(3, 3);

        // then
        assertThat(pixel03).isSameAs(pixel00);
        assertThat(pixel30).isSameAs(pixel00);
        assertThat(pixel33).isSameAs(pixel00);
    }
}
