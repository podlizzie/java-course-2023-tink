package edu.project2;

import edu.project2.model.Maze;
import edu.project2.utility.generation.PrimGeneration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PrimGenerationTest {

    @DisplayName("Test generating a valid maze")
    @ParameterizedTest(name = "Generate maze with height={0} and width={1}")
    @CsvSource({
        "5, 5",
        "10, 10",
        "15, 20"
    })
    public void testGenerateValidMaze(int height, int width) {
        PrimGeneration generator = new PrimGeneration();
        Maze maze = generator.generate(height, width);

        // Assert that the generated maze has the correct dimensions
        assertThat(maze.height()).isEqualTo(height);
        assertThat(maze.width()).isEqualTo(width);
    }

    @Test
    @DisplayName("Test generating a maze with negative height")
    public void testGenerateMazeWithNegativeHeight() {
        PrimGeneration generator = new PrimGeneration();
        assertThrows(IllegalArgumentException.class, () -> generator.generate(-5, 10));
    }

    @Test
    @DisplayName("Test generating a maze with negative width")
    public void testGenerateMazeWithNegativeWidth() {
        PrimGeneration generator = new PrimGeneration();
        assertThrows(IllegalArgumentException.class, () -> generator.generate(5, -10));
    }
}
