package edu.project4.imageRenderer;

import edu.project4.entity.Canvas;
import edu.project4.fractalGeneration.FractalImage;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.imageio.ImageIO;

public class ImageUtils {
    private ImageUtils() {
    }

    public static void renderImage(
        FractalImage fractalImage,
        Path directoryPath,
        String fileName,
        ImageFormat fileFormat
    ) {
        if (!Files.isDirectory(directoryPath)) {
            throw new IllegalArgumentException("directoryPath should be a path of some directory");
        }
        int height = fractalImage.height();
        int width = fractalImage.width();

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        fillGraphics(fractalImage, graphics);

        saveFile(image, directoryPath, fileName, fileFormat);
    }

    private static void fillGraphics(FractalImage fractalImage, Graphics graphics) {
        for (int i = 0; i < fractalImage.width(); i++) {
            for (int j = 0; j < fractalImage.height(); j++) {
                var pixel = fractalImage.pixel(i, j);
                graphics.setColor(new Color(pixel.getR(), pixel.getG(), pixel.getB()));
                graphics.fillRect(i, j, 1, 1);
            }
        }
    }


    private static void saveFile(BufferedImage image, Path directoryPath, String fileName, ImageFormat fileFormat) {
        String fullFileName = directoryPath + "\\" + fileName + "." + fileFormat;
        int i = 0;
        while (Files.exists(Path.of(fullFileName))) {
            i++;
            fullFileName = directoryPath + "\\" + fileName + i + "." + fileFormat;
        }

        try {
            ImageIO.write(image, fileFormat.toString(), new File(fullFileName));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
