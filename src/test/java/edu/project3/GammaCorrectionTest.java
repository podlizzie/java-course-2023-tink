package edu.project3;

import edu.project4.entity.PixelList;
import edu.project4.entity.Point;
import edu.project4.postProcessing.GammaCorrection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.awt.Color;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GammaCorrectionTest {
    private static final int WIDTH = 2;
    private static final int HEIGHT = 2;

    private final GammaCorrection correction = new GammaCorrection();

    @Test
    @DisplayName("Gamma correction should recolored pixels")
    void testThatGammaCorrectionRecoloredPixels() {
        //given
        PixelList canvas = new PixelList(HEIGHT, WIDTH, false, false);
        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 1);
        Color defaultColor = canvas.getPixel(p1).getColor();

        //when
        canvas.getPixel(p1).setHits(100);
        canvas.getPixel(p2).setHits(50);
        canvas.getPixel(p2).setColor(new Color(100, 100, 100));
        correction.applyGammaCorrection(canvas);
        Color dot2Color = canvas.getPixel(p2).getColor();

        //then
        assertThat(dot2Color).isNotEqualTo(defaultColor);
    }
}
