package edu.project4.fractalGeneration;

import edu.project4.entity.Pixel;
import edu.project4.entity.PixelList;
import edu.project4.entity.Point;
import edu.project4.transformation.AffineGenerator;
import edu.project4.transformation.Transformation;
import java.awt.Color;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.jetbrains.annotations.NotNull;

public abstract class RendererUtils {
    protected static final double X_MIN = -1.83;
    protected static final double X_MAX = 1.83;
    protected static final double Y_MIN = -1;
    protected static final double Y_MAX = 1;

    protected abstract void render(
        PixelList pixelList,
        int samples,
        int iterationsPerSample,
        List<AffineGenerator> affineTransformations,
        List<Transformation> pointFunctions
    );

    protected static Point getRandomInitialPoint() {
        return new Point(
            ThreadLocalRandom.current().nextDouble(X_MIN, X_MAX),
            ThreadLocalRandom.current().nextDouble(Y_MIN, Y_MAX)
        );
    }

    protected static Point applyPointFunctions(Point point, @NotNull List<Transformation> functions) {
        Point resPoint = functions.get(0).apply(point);
        for (int i = 1; i < functions.size(); i++) {
            resPoint = functions.get(i).apply(resPoint);
        }
        return resPoint;
    }

    protected static Point getNewPoint(@NotNull Point point, int height, int width) {
        double x = height - (((X_MAX - point.x()) / (X_MAX - X_MIN)) * height);
        double y = width - (((Y_MAX - point.y()) / (Y_MAX - Y_MIN)) * width);

        return new Point(x, y);
    }

    protected static void coloredPixel(Point point, @NotNull PixelList pixelList, Color transformationColor) {
        Pixel pixel = pixelList.getPixel(point);
        if (pixel.getHits() == 0) {
            pixel.setColor(transformationColor);
        } else {
            pixel.mixColor(transformationColor);
        }
        pixel.incrementHits();
    }

    protected static AffineGenerator getRandomAffineTransformations(@NotNull List<AffineGenerator> list) {
        return list.get(ThreadLocalRandom.current().nextInt(0, list.size()));
    }
}
