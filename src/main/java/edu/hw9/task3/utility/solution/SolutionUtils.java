package edu.hw9.task3.utility.solution;

import edu.project2.model.Cell;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SolutionUtils {
    private SolutionUtils() {

    }

    /**
     * Finds the unvisited neighbors of a coordinate in a maze.
     *
     * @param maze       the maze
     * @param coordinate the coordinate
     * @param visited    the array of visited cells
     * @return the list of unvisited neighbors
     */
    public static List<Coordinate> findNeighbors(Maze maze, Coordinate coordinate, boolean[][] visited) {
        return getNeighbors(coordinate)
            .filter(neighbor -> isValidNeighbor(neighbor, maze, visited))
            .collect(Collectors.toList());
    }

    /**
     * Retrieves the neighbors of a given coordinate.
     *
     * @param coordinate the coordinate
     * @return the stream of neighbor coordinates
     */
    private static Stream<Coordinate> getNeighbors(Coordinate coordinate) {
        int row = coordinate.row();
        int col = coordinate.col();

        Coordinate top = new Coordinate(row - 1, col);
        Coordinate bottom = new Coordinate(row + 1, col);
        Coordinate left = new Coordinate(row, col - 1);
        Coordinate right = new Coordinate(row, col + 1);

        return Stream.of(top, bottom, left, right);
    }

    /**
     * Checks if a neighbor coordinate is valid and unvisited.
     *
     * @param neighbor the neighbor coordinate
     * @param maze     the maze
     * @param visited  the array of visited cells
     * @return true if the neighbor coordinate is valid and unvisited, false otherwise
     */
    private static boolean isValidNeighbor(Coordinate neighbor, Maze maze, boolean[][] visited) {
        int row = neighbor.row();
        int col = neighbor.col();

        return isWithinBounds(neighbor, maze)
            && !visited[row][col]
            && (maze.grid()[row][col].getType()).equals(Cell.Type.PASSAGE);
    }

    /**
     * Checks if a coordinate is within the bounds of a maze.
     *
     * @param coordinate the coordinate
     * @param maze       the maze
     * @return true if the coordinate is within the bounds of the maze, false otherwise
     */
    public static boolean isWithinBounds(Coordinate coordinate, Maze maze) {
        return coordinate.row() >= 0 && coordinate.row() < maze.height()
            && coordinate.col() >= 0 && coordinate.col() < maze.width();
    }
}
