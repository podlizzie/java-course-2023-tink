package edu.project4;

import edu.project4.entity.PixelList;
import edu.project4.fractalGeneration.SingleThreadedRenderer;
import edu.project4.imageRenderer.ImageFormat;
import edu.project4.imageRenderer.ImageUtils;
import edu.project4.postProcessing.GammaCorrection;
import edu.project4.transformation.AffineUtils;
import edu.project4.transformation.DiskFunction;
import edu.project4.transformation.SwirlFunction;
import java.nio.file.Path;
import java.util.List;

public class Main {
    private Main() {

    }

    private static final int HEIGHT = 1980;
    private static final int WIDTH = 1080;
    private static final boolean HAS_VERTICAL_SYMMETRY = false;
    private static final boolean HAS_HORIZONTAL_SYMMETRY = false;
    private static final int SAMPLES = 400;
    private static final int ITERATIONS = 1000000;
    private static final int AFFINE_TRANSFORM_COUNT = 30;
    private static final String DIR_EXAMPLE = "src/main/resources/fractalExamples";
    private static final String FILENAME = "test";

    public static void main(String[] args) {

        // code run example

        PixelList pixelList = new PixelList(HEIGHT, WIDTH, HAS_VERTICAL_SYMMETRY, HAS_HORIZONTAL_SYMMETRY);
        SingleThreadedRenderer singleThreadedRenderer = new SingleThreadedRenderer();
        singleThreadedRenderer.render(
            pixelList,
            SAMPLES,
            ITERATIONS,
            AffineUtils.getListOfAffineTransformations(AFFINE_TRANSFORM_COUNT),
            List.of(new SwirlFunction(), new DiskFunction())
        );

        GammaCorrection gammaCorrection = new GammaCorrection();
        gammaCorrection.applyGammaCorrection(pixelList);

        ImageUtils.renderImage(pixelList, Path.of(DIR_EXAMPLE), FILENAME, ImageFormat.PNG);
    }
}
