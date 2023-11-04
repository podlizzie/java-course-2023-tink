package edu.project2.utility.generation;

import edu.project2.model.Cell;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class DFSGeneration implements Generator {

    /**
     * Generates a maze with the specified height and width using the DFS algorithm.
     *
     * @param height the height of the maze
     * @param width  the width of the maze
     * @return the generated maze
     * @throws IllegalArgumentException if the height or width is less than or equal to 0
     */
    @Override
    public Maze generate(int height, int width) {
        if (height <= 0 || width <= 0) {
            throw new IllegalArgumentException("Height and width must be greater than 0");
        }
        Cell[][] grid = new Cell[height][width];

        // Initialize maze with walls
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                grid[row][col] = new Cell(row, col, Cell.Type.WALL);
            }
        }

        Random random = new Random();
        Stack<Coordinate> stack = new Stack<>();
        Coordinate start = new Coordinate(0, 0);
        grid[start.row()][start.col()].setType(Cell.Type.PASSAGE);
        stack.push(start);

        dfs(grid, stack, random);

        return new Maze(height, width, grid);
    }

    /**
     * Applies the DFS algorithm to generate a maze.
     *
     * @param grid   the maze grid
     * @param stack  the stack used for backtracking during the algorithm
     * @param random the random number generator
     */
    private void dfs(Cell[][] grid, Stack<Coordinate> stack, Random random) {
        while (!stack.isEmpty()) {
            Coordinate current = stack.peek();
            List<Coordinate> neighbors = getNeighbors(current, grid);
            if (!neighbors.isEmpty()) {
                Coordinate next = neighbors.get(random.nextInt(neighbors.size()));
                removeWall(current, next, grid);
                grid[next.row()][next.col()].setType(Cell.Type.PASSAGE);
                stack.push(next);
            } else {
                stack.pop();
            }
        }
    }

    /**
     * Gets neighbors of a given coordinate in the maze.
     *
     * @param current the current coordinate
     * @param grid    the maze grid
     * @return the list of unvisited neighbors
     */
    private List<Coordinate> getNeighbors(Coordinate current, Cell[][] grid) {
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

    /**
     * Removes the wall between two given coordinates in the maze.
     *
     * @param current the current coordinate
     * @param next    the next coordinate
     * @param grid    the maze grid
     */
    private void removeWall(Coordinate current, Coordinate next, Cell[][] grid) {
        int row = (current.row() + next.row()) / 2;
        int col = (current.col() + next.col()) / 2;
        grid[row][col].setType(Cell.Type.PASSAGE);
    }
}
