package edu.project4.postProcessing;

import edu.project4.entity.Pixel;
import edu.project4.entity.PixelList;
import java.awt.Color;

public class GammaCorrection implements PostProcessing {

    private static final double GAMMA = 3;
    private static final int MAX_COLOR = 255;

    @Override
    public void applyGammaCorrection(PixelList pixelList) {
        double max = 0.0;

        for (int row = 0; row < pixelList.getHeight(); row++) {
            for (int col = 0; col < pixelList.getWidth(); col++) {
                Pixel pixel = pixelList.getPixel(row, col);
                if (pixel.getHits() != 0) {
                    pixel.setNormal(Math.log10(pixel.getHits()));
                    if (pixel.getNormal() > max) {
                        max = pixel.getNormal();
                    }
                }
            }
        }

        for (int row = 0; row < pixelList.getHeight(); row++) {
            for (int col = 0; col < pixelList.getWidth(); col++) {
                Pixel pixel = pixelList.getPixel(row, col);
                double normalized = pixel.getNormal() / max;

                Color originalColor = pixel.getColor();
                Color correctedColor = new Color(
                    correctColorComponent(originalColor.getRed(), normalized),
                    correctColorComponent(originalColor.getGreen(), normalized),
                    correctColorComponent(originalColor.getBlue(), normalized)
                );

                pixel.setColor(correctedColor);
            }
        }
    }

    private int correctColorComponent(int colorComponent, double normalizedValue) {
        double correctedComponent = colorComponent * Math.pow(normalizedValue, 1.0 / GAMMA);
        return (int) Math.min(MAX_COLOR, Math.max(0, correctedComponent));
    }
}
