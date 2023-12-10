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
        PixelList pixelList = new PixelList(HEIGHT, WIDTH, false, false);
        Point p1 = new Point(0, 0);

        //when
        pixelList.getPixel(p1).setHits(3);
        pixelList.getPixel(p1).setColor(new Color(173, 128, 128));
        Color pixelStartColor = pixelList.getPixel(p1).getColor();
        correction.applyGammaCorrection(pixelList);
        Color pixelEndColor = pixelList.getPixel(p1).getColor();

        //then
        assertThat(pixelEndColor).isNotEqualTo(pixelStartColor);
    }
}
