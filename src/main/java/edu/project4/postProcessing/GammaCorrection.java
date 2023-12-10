package edu.project4.postProcessing;

import edu.project4.entity.Pixel;
import edu.project4.entity.PixelList;
import edu.project4.postProcessing.PostProcessing;
import java.awt.Color;

public class GammaCorrection implements PostProcessing {

    @Override
    public void applyGammaCorrection(PixelList pixelList) {
        double max = 0.0;
        double gamma = 2.2;

        // Calculate the log of hits for each pixel and find the max value
        for (int row = 0; row < pixelList.getHeight(); row++) {
            for (int col = 0; col < pixelList.getWidth(); col++) {
                Pixel pixel = pixelList.getPixel(row, col);
                if (pixel.getHits() != 0) {
                    double normal = Math.log10(pixel.getHits());
                    pixel.setNormal(normal);
                    if (normal > max) {
                        max = normal;
                    }
                }
            }
        }

        // Normalize the normal factor and apply gamma correction
        for (int row = 0; row < pixelList.getHeight(); row++) {
            for (int col = 0; col < pixelList.getWidth(); col++) {
                Pixel pixel = pixelList.getPixel(row, col);
                double normalized = pixel.getNormal() / max;
                pixel.setNormal(normalized);

                Color currentColor = pixel.getColor();
                int red = applyGammaCorrection(currentColor.getRed(), normalized, gamma);
                int green = applyGammaCorrection(currentColor.getGreen(), normalized, gamma);
                int blue = applyGammaCorrection(currentColor.getBlue(), normalized, gamma);

                pixel.setColor(new Color(red, green, blue));
            }
        }
    }

    private int applyGammaCorrection(int colorComponent, double normalized, double gamma) {
        return (int) (255.0 * Math.pow((colorComponent / 255.0) * normalized, 1.0 / gamma));
    }
}
