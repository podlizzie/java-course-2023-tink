package edu.project3;

import edu.project4.entity.PixelList;
import edu.project4.fractalGeneration.SingleThreadedRenderer;
import edu.project4.postProcessing.GammaCorrection;
import edu.project4.transformation.AffineUtils;
import edu.project4.transformation.DiskFunction;
import java.awt.Color;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GammaCorrectionTest {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;

    private final GammaCorrection correction = new GammaCorrection();

    @Test
    @DisplayName("Gamma correction should recolored pixels")
    void testThatGammaCorrectionRecoloredPixels() {
        //given
        PixelList pixelList = new PixelList(HEIGHT, WIDTH, false, false);
        SingleThreadedRenderer singleThreadedRenderer = new SingleThreadedRenderer();
        singleThreadedRenderer.render(
            pixelList,
            500,
            1000,
            AffineUtils.getListOfAffineTransformations(20),
            List.of(new DiskFunction())
        );

        //when
        Color pixelStartColor = pixelList.getPixel(340, 340).getColor();
        GammaCorrection gammaCorrection = new GammaCorrection();
        gammaCorrection.applyGammaCorrection(pixelList);
        Color pixelEndColor = pixelList.getPixel(340, 340).getColor();

        //then
        assertThat(pixelEndColor).isNotEqualTo(pixelStartColor);
    }
}
