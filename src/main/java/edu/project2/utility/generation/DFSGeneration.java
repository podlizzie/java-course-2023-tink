package edu.project2.utility.generation;

import edu.project2.model.Cell;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
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
        GenerationUtils.checkInput(height, width);

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
            List<Coordinate> neighbors = GenerationUtils.getNeighbors(current, grid);
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
