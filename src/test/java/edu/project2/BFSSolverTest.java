package edu.project2;

import edu.project2.model.Cell;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import edu.project2.utility.solution.BFSSolver;
import edu.project2.utility.solution.Solver;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class BFSSolverTest {

    @Test
    @DisplayName("Test solving a maze with a valid solution")
    public void testSolveMazeWithValidSolution() {
        // Create a sample maze
        Cell[][] grid = {
            {new Cell(0, 0, Cell.Type.PASSAGE), new Cell(0, 1, Cell.Type.WALL), new Cell(0, 2, Cell.Type.PASSAGE)},
            {new Cell(1, 0, Cell.Type.PASSAGE), new Cell(1, 1, Cell.Type.PASSAGE), new Cell(1, 2, Cell.Type.PASSAGE)},
            {new Cell(2, 0, Cell.Type.PASSAGE), new Cell(2, 1, Cell.Type.WALL), new Cell(2, 2, Cell.Type.PASSAGE)}
        };
        Maze maze = new Maze(3, 3, grid);
        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(2, 2);

        Solver solver = new BFSSolver();
        List<Coordinate> path = solver.solve(maze, start, end);

        List<Coordinate> expectedPath = List.of(
            new Coordinate(0, 0),
            new Coordinate(1, 0),
            new Coordinate(1, 1),
            new Coordinate(1, 2),
            new Coordinate(2, 2)
        );
        assertThat(path).isEqualTo(expectedPath);
    }

    @Test
    @DisplayName("Test solving a maze with no valid solution")
    public void testSolveMazeWithNoSolution() {
        // Create a sample maze with no valid solution
        Cell[][] grid = {
            {new Cell(0, 0, Cell.Type.WALL), new Cell(0, 1, Cell.Type.WALL)},
            {new Cell(1, 0, Cell.Type.WALL), new Cell(1, 1, Cell.Type.PASSAGE)}
        };
        Maze maze = new Maze(2, 2, grid);
        Coordinate start = new Coordinate(0, 0); //WALL
        Coordinate end = new Coordinate(1, 1);

        Solver solver = new BFSSolver();
        List<Coordinate> path = solver.solve(maze, start, end);

        assertThat(path).isEmpty();
    }

    @Test
    @DisplayName("Test solving a maze with invalid start or end coordinates")
    public void testSolveMazeWithInvalidCoordinates() {
        // Create a sample maze
        Cell[][] grid = {
            {new Cell(0, 0, Cell.Type.PASSAGE), new Cell(0, 1, Cell.Type.PASSAGE)},
            {new Cell(1, 0, Cell.Type.PASSAGE), new Cell(1, 1, Cell.Type.PASSAGE)}
        };
        Maze maze = new Maze(2, 2, grid);
        Coordinate start = new Coordinate(0, -1); // Invalid start coordinate
        Coordinate end = new Coordinate(2, 2); // Invalid end coordinate

        Solver solver = new BFSSolver();

        assertThatThrownBy(() -> solver.solve(maze, start, end))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Start or end coordinates are out of bounds.");
    }
}
