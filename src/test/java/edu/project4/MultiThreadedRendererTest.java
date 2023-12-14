package edu.project4;

import edu.project4.entity.PixelList;
import edu.project4.fractalGeneration.MultiThreadedRenderer;
import edu.project4.transformation.AffineGenerator;
import edu.project4.transformation.AffineUtils;
import edu.project4.transformation.SinusoidalFunction;
import edu.project4.transformation.Transformation;
import java.awt.Color;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MultiThreadedRendererTest {
    private static final int SAMPLES = 1000000;
    private static final int ITERATIONS = 100;
    private static final int HEIGHT = 100;
    private static final int WIDTH = 100;

    private int getCountOfBlackPixels(PixelList pixelList) {
        Color black = new Color(0, 0, 0);
        int count = 0;
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (pixelList.getPixel(i, j).getColor().equals(black)) {
                    count++;
                }
            }
        }
        return count;
    }

    @Test
    @DisplayName("MultiThreadedRenderer should given PixelCanvas with not height * width black pixels")
    void testThatMultiThreadedRendererShouldFill() {
        PixelList pixelList = new PixelList(HEIGHT, WIDTH, false, false);
        List<Transformation> functions = List.of(new SinusoidalFunction());

        List<AffineGenerator> affineTransformations = AffineUtils.getListOfAffineTransformations(5);

        MultiThreadedRenderer multiThreadedRenderer = new MultiThreadedRenderer();

        multiThreadedRenderer.render(
            pixelList,
            SAMPLES,
            ITERATIONS,
            affineTransformations,
            functions
        );

        int countOfBlackPixels = getCountOfBlackPixels(pixelList);

        assertThat(countOfBlackPixels).isNotEqualTo(HEIGHT * WIDTH);
    }
}
