package edu.project4.fractalGeneration;

import edu.project4.entity.Pixel;
import edu.project4.entity.PixelList;
import edu.project4.entity.Point;
import edu.project4.transformation.AffineGenerator;
import edu.project4.transformation.Transformation;
import java.awt.Color;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class MultiThreadedRenderer extends RendererUtils {
    private static final int THREAD_COUNT = Runtime.getRuntime().availableProcessors();

    @Override
    public void render(
        PixelList pixelList,
        int samples,
        int iterationsPerSample,
        List<AffineGenerator> affineTransformations,
        List<Transformation> pointFunctions
    ) {
        AtomicIntegerArray atomicPixelArray = new AtomicIntegerArray(pixelList.getWidth() * pixelList.getHeight());
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        for (int sample = 0; sample < samples; sample++) {
            executorService.submit(() -> {
                Point newPoint = getRandomInitialPoint();
                for (int iteration = PRE_STEPS; iteration < iterationsPerSample; iteration++) {
                    AffineGenerator transformation = getRandomAffineTransformations(affineTransformations);

                    newPoint = transformation.apply(applyPointFunctions(newPoint, pointFunctions));

                    if (iteration >= 0
                        && (newPoint.x() >= X_MIN && newPoint.x() <= X_MAX)
                        && (newPoint.y() >= Y_MIN && newPoint.y() <= Y_MAX)) {

                        Point pw = getNewPoint(newPoint, pixelList.getHeight(), pixelList.getWidth());

                        if (pw.x() < pixelList.getHeight() && pw.y() < pixelList.getWidth()) {
                            coloredPixel(pw, pixelList, atomicPixelArray, transformation.getColor());
                        }
                    }
                }
            });
        }
        executorService.shutdown();
    }

    private void coloredPixel(
        Point pw,
        PixelList pixelList,
        AtomicIntegerArray atomicPixelArray,
        Color transformation
    ) {
        int index = (int) (pw.x() * pixelList.getWidth() + pw.y());
        int currentHits = atomicPixelArray.getAndIncrement(index);

        Pixel pixel = pixelList.getPixel(pw.x(), pw.y());

        if (currentHits == 0) {
            pixel.setColor(transformation);
        } else {
            pixel.mixColor(transformation);
        }
        pixel.incrementHits();
    }

}
