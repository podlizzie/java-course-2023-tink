package edu.project2.utility.generation;

import edu.project2.model.Cell;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import java.util.ArrayList;
import java.util.List;

public class GenerationUtils {

    private GenerationUtils() {

    }

    /**
     * Gets neighbors of a given coordinate in the maze.
     *
     * @param current the current coordinate
     * @param grid    the maze grid
     * @return the list of unvisited neighbors
     */
    public static List<Coordinate> getNeighbors(Coordinate current, Cell[][] grid) {
        int row = current.row();
        int col = current.col();
        int height = grid.length;
        int width = grid[0].length;

        List<Coordinate> neighbors = new ArrayList<>();
        if (col - 2 >= 0 && grid[row][col - 2].getType() == Cell.Type.WALL) {
            neighbors.add(new Coordinate(row, col - 2));
        }
        if (col + 2 < width && grid[row][col + 2].getType() == Cell.Type.WALL) {
            neighbors.add(new Coordinate(row, col + 2));
        }
        if (row - 2 >= 0 && grid[row - 2][col].getType() == Cell.Type.WALL) {
            neighbors.add(new Coordinate(row - 2, col));
        }
        if (row + 2 < height && grid[row + 2][col].getType() == Cell.Type.WALL) {
            neighbors.add(new Coordinate(row + 2, col));
        }

        return neighbors;
    }

    public static Maze initializeMaze(int height, int width) {
        Cell[][] grid = new Cell[height][width];
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                grid[row][col] = new Cell(row, col, Cell.Type.WALL);
            }
        }
        return new Maze(height, width, grid);
    }

    public static void checkInput(int height, int width) {
        if (height <= 0 || width <= 0) {
            throw new IllegalArgumentException("Height and width must be greater than 0");
        }
    }
}
