package edu.project4.imageRenderer;

import edu.project4.entity.PixelList;
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
        PixelList pixelList,
        Path directoryPath,
        String fileName,
        ImageFormat fileFormat
    ) {
        if (!Files.isDirectory(directoryPath)) {
            throw new IllegalArgumentException("Directory path should be a path of some directory");
        }
        int height = pixelList.getHeight();
        int width = pixelList.getWidth();

        BufferedImage image = new BufferedImage(height, width, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        fillGraphics(pixelList, graphics);

        saveToFile(image, directoryPath, fileName, fileFormat);
    }

    private static void fillGraphics(PixelList pixelList, Graphics graphics) {
        for (int i = 0; i < pixelList.getHeight(); i++) {
            for (int j = 0; j < pixelList.getWidth(); j++) {
                var pixel = pixelList.getPixel(i, j);
                graphics.setColor(pixel.getColor());
                graphics.fillRect(i, j, 1, 1);
            }
        }
    }


    private static void saveToFile(BufferedImage image, Path directoryPath, String fileName, ImageFormat imageFormat) {
        String fullFileName = directoryPath + "/" + fileName + "." + imageFormat;
        int i = 0;
        while (Files.exists(Path.of(fullFileName))) {
            i++;
            fullFileName = directoryPath + "/" + fileName + i + "." + imageFormat;
        }

        try {
            ImageIO.write(image, imageFormat.toString(), new File(fullFileName));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
