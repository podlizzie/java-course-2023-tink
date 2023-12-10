package edu.project4.fractalGeneration;

import edu.project4.entity.PixelList;
import edu.project4.entity.Point;
import edu.project4.transformation.AffineGenerator;
import edu.project4.transformation.Transformation;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MultiThreadedRenderer extends RendererUtils {

    private static final int THREAD_COUNT = Runtime.getRuntime().availableProcessors();
    private final ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);

    @Override
    public void render(
        PixelList pixelList,
        int samples,
        int iterationsPerSample,
        List<AffineGenerator> affineTransformations,
        List<Transformation> pointFunctions
    ) {
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
                            synchronized (pixelList) {
                                coloredPixel(pw, pixelList, transformation.getColor());
                            }
                        }
                    }
                }
            });
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
