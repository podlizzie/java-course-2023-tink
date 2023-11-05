package edu.project2;

import edu.project2.model.Cell;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import edu.project2.utility.solution.BFSSolver;
import edu.project2.view.ConsoleRenderer;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ConsoleRendererTest {
    @Test
    @DisplayName("Test that the rendered maze without solution matches the expected output")
    public void testThatRenderedMazeMatchesExpectedOutput() {
        // Create a sample maze
        Cell[][] grid = {
            {new Cell(0, 0, Cell.Type.PASSAGE), new Cell(0, 1, Cell.Type.WALL), new Cell(0, 2, Cell.Type.PASSAGE),
                new Cell(0, 3, Cell.Type.WALL), new Cell(0, 4, Cell.Type.PASSAGE)},
            {new Cell(1, 0, Cell.Type.PASSAGE), new Cell(1, 1, Cell.Type.WALL), new Cell(1, 2, Cell.Type.PASSAGE),
                new Cell(1, 3, Cell.Type.WALL), new Cell(1, 4, Cell.Type.PASSAGE)},
            {new Cell(2, 0, Cell.Type.PASSAGE), new Cell(2, 1, Cell.Type.WALL), new Cell(2, 2, Cell.Type.WALL),
                new Cell(2, 3, Cell.Type.WALL), new Cell(2, 4, Cell.Type.PASSAGE)},
            {new Cell(3, 0, Cell.Type.PASSAGE), new Cell(3, 1, Cell.Type.WALL), new Cell(3, 2, Cell.Type.WALL),
                new Cell(3, 3, Cell.Type.WALL), new Cell(3, 4, Cell.Type.PASSAGE)},
            {new Cell(4, 0, Cell.Type.PASSAGE), new Cell(4, 1, Cell.Type.PASSAGE), new Cell(4, 2, Cell.Type.PASSAGE),
                new Cell(4, 3, Cell.Type.PASSAGE), new Cell(4, 4, Cell.Type.PASSAGE)}
        };
        Maze maze = new Maze(5, 5, grid);

        // Create the expected output
        String expectedOutput =
            "━━━━━━━━━━━" + System.lineSeparator() +
            "┃  ▒   ▒   ┃" + System.lineSeparator() +
            "┃  ▒   ▒   ┃" + System.lineSeparator() +
            "┃  ▒ ▒ ▒   ┃" + System.lineSeparator() +
            "┃  ▒ ▒ ▒   ┃" + System.lineSeparator() +
            "┃          ┃" + System.lineSeparator() +
            "━━━━━━━━━━━" + System.lineSeparator();

        // Render the maze
        ConsoleRenderer renderer = new ConsoleRenderer();
        String renderedMaze = renderer.render(maze);

        // Assert that the rendered maze matches the expected output
        assertThat(renderedMaze).isEqualTo(expectedOutput);
    }

    @Test
    @DisplayName("Test that the rendered maze with solution matches the expected output")
    public void testThatRenderedMazeWithSolutionMatchesExpectedOutput() {
        Cell[][] grid = {
            {new Cell(0, 0, Cell.Type.PASSAGE), new Cell(0, 1, Cell.Type.WALL), new Cell(0, 2, Cell.Type.PASSAGE),
                new Cell(0, 3, Cell.Type.WALL), new Cell(0, 4, Cell.Type.PASSAGE)},
            {new Cell(1, 0, Cell.Type.PASSAGE), new Cell(1, 1, Cell.Type.WALL), new Cell(1, 2, Cell.Type.PASSAGE),
                new Cell(1, 3, Cell.Type.WALL), new Cell(1, 4, Cell.Type.PASSAGE)},
            {new Cell(2, 0, Cell.Type.PASSAGE), new Cell(2, 1, Cell.Type.WALL), new Cell(2, 2, Cell.Type.WALL),
                new Cell(2, 3, Cell.Type.WALL), new Cell(2, 4, Cell.Type.PASSAGE)},
            {new Cell(3, 0, Cell.Type.PASSAGE), new Cell(3, 1, Cell.Type.WALL), new Cell(3, 2, Cell.Type.WALL),
                new Cell(3, 3, Cell.Type.WALL), new Cell(3, 4, Cell.Type.PASSAGE)},
            {new Cell(4, 0, Cell.Type.PASSAGE), new Cell(4, 1, Cell.Type.PASSAGE), new Cell(4, 2, Cell.Type.PASSAGE),
                new Cell(4, 3, Cell.Type.PASSAGE), new Cell(4, 4, Cell.Type.PASSAGE)}
        };
        Maze maze = new Maze(5, 5, grid);

        // Create the expected output
        String expectedOutput =
            "━━━━━━━━━━━" + System.lineSeparator() +
            "┃S ▒   ▒   ┃" + System.lineSeparator() +
            "┃* ▒   ▒   ┃" + System.lineSeparator() +
            "┃* ▒ ▒ ▒   ┃" + System.lineSeparator() +
            "┃* ▒ ▒ ▒   ┃" + System.lineSeparator() +
            "┃* * * * E ┃" + System.lineSeparator() +
            "━━━━━━━━━━━" + System.lineSeparator();

        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(4, 4);
        BFSSolver bfsSolver = new BFSSolver();
        List<Coordinate> path = bfsSolver.solve(maze, start, end);

        ConsoleRenderer renderer = new ConsoleRenderer();
        String renderedMaze = renderer.render(maze, path);

        // Assert that the rendered maze matches the expected output
        assertThat(renderedMaze).isEqualTo(expectedOutput);
    }
}
