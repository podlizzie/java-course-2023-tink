package edu.project4;

import edu.project4.entity.PixelList;
import edu.project4.fractalGeneration.MultiThreadedRenderer;
import edu.project4.fractalGeneration.SingleThreadedRenderer;
import edu.project4.transformation.AffineGenerator;
import edu.project4.transformation.AffineUtils;
import edu.project4.transformation.RombFunction;
import edu.project4.transformation.Transformation;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TimeTest {
    private static final int SAMPLES = 10000;
    private static final int ITERATIONS = 100;
    private static final int HEIGHT = 100;
    private static final int WIDTH = 100;

    @Test
    @DisplayName("Test that multi threaded is faster than single threaded")
    public void testThatMultiThreadedIsFasterThanSingleThreaded() {

        System.out.println("single: " + calculateSingleTime());
        System.out.println("multi: " + calculateMultiTime());

        boolean result = calculateMultiTime() < calculateSingleTime();
        assertThat(result).isTrue();
    }

    private long calculateSingleTime() {
        PixelList pixelList = new PixelList(HEIGHT, WIDTH, false, false);
        List<AffineGenerator> affineTransformations = AffineUtils.getListOfAffineTransformations(10);
        List<Transformation> transformations = List.of(new RombFunction());

        SingleThreadedRenderer singleThreadedRenderer = new SingleThreadedRenderer();

        long startTimeMultiThreaded = System.nanoTime();
        singleThreadedRenderer.render(pixelList, SAMPLES, ITERATIONS, affineTransformations, transformations);
        long endTimeMultiThreaded = System.nanoTime();

        return (endTimeMultiThreaded - startTimeMultiThreaded);
    }

    private long calculateMultiTime() {
        PixelList pixelList = new PixelList(HEIGHT, WIDTH, false, false);
        List<AffineGenerator> affineTransformations = AffineUtils.getListOfAffineTransformations(10);
        List<Transformation> transformations = List.of(new RombFunction());

        MultiThreadedRenderer multiThreadedRenderer = new MultiThreadedRenderer();

        long startTimeMultiThreaded = System.nanoTime();
        multiThreadedRenderer.render(pixelList, SAMPLES, ITERATIONS, affineTransformations, transformations);
        long endTimeMultiThreaded = System.nanoTime();

        return (endTimeMultiThreaded - startTimeMultiThreaded);
    }
}
