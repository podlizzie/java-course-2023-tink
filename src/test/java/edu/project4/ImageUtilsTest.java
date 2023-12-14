package edu.project4;

import edu.project4.entity.PixelList;
import edu.project4.imageRenderer.ImageFormat;
import edu.project4.imageRenderer.ImageUtils;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ImageUtilsTest {
    @TempDir
    Path tempDir;

    @Test
    @DisplayName("Test that renderImage returns a correctly sized image")
    public void testThatRenderImageReturnedCorrectlySizedImage() {
        // Given
        PixelList pixelList = new PixelList(10, 10, false, false);
        String fileName = "testImage";
        ImageFormat fileFormat = ImageFormat.PNG;

        // When
        ImageUtils.renderImage(pixelList, tempDir, fileName, fileFormat);
        Path imagePath = Paths.get(tempDir.toString(), fileName + "." + fileFormat);

        // Then
        assertThat(Files.exists(imagePath)).isTrue();
        assertThat(imagePath.toFile().length()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Test that renderImage throws IllegalArgumentException for non-directory path")
    public void testThatRenderImageThrowsIllegalArgumentExceptionForNonDirectoryPath() {
        Assertions.assertThatThrownBy(() -> {
                // Given
                PixelList pixelList = new PixelList(10, 10, false, false);
                Path file = tempDir.resolve("not_a_directory");
                Files.createFile(file);
                String fileName = "testImage";
                ImageFormat fileFormat = ImageFormat.PNG;

                // When
                ImageUtils.renderImage(pixelList, file, fileName, fileFormat);
            })
            // Then
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Directory path should be a path of some directory");
    }

}
