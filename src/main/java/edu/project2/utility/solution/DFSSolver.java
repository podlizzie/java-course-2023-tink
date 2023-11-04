package edu.project2.utility.solution;

import edu.project2.model.Cell;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class DFSSolver implements Solver {

    /**
     * Solves the maze using the DFS algorithm.
     *
     * @param maze  the maze to solve
     * @param start the start coordinate
     * @param end   the end coordinate
     * @return the list of coordinates representing the solution path
     * @throws IllegalArgumentException if the start or end cell is a wall or if the start
     *                                  or end coordinates are out of bounds
     */
    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        List<Coordinate> path = new ArrayList<>();
        Stack<Coordinate> stack = new Stack<>();
        boolean[][] visited = new boolean[maze.height()][maze.width()];

        // Check whether the start or end coordinates are within the maze
        if (!isWithinBounds(start, maze) || !isWithinBounds(end, maze)) {
            throw new IllegalArgumentException("Start or end coordinates are out of bounds.");
        }

        // Check if the start or end cell is a wall
        if (maze.grid()[start.row()][start.col()].getType() == Cell.Type.WALL
            || maze.grid()[end.row()][end.col()].getType() == Cell.Type.WALL) {
            return path;
        }

        stack.push(start);
        visited[start.row()][start.col()] = true;

        dfs(maze, stack, visited, end, path);

        return path;
    }

    private boolean isWithinBounds(Coordinate coordinate, Maze maze) {
        return coordinate.row() >= 0 && coordinate.row() < maze.height()
            && coordinate.col() >= 0 && coordinate.col() < maze.width();
    }

    /**
     * Applies the DFS algorithm to solve the maze.
     *
     * @param maze    the maze
     * @param stack   the stack used for backtracking during the algorithm
     * @param visited the array tracking visited cells
     * @param end     the end coordinate
     * @param path    the list storing the solution path
     */
    private void dfs(Maze maze, Stack<Coordinate> stack, boolean[][] visited, Coordinate end, List<Coordinate> path) {
        while (!stack.isEmpty()) {
            Coordinate current = stack.peek();

            if (current.equals(end)) {
                for (Coordinate coordinate : stack) {
                    path.add(new Coordinate(coordinate.row(), coordinate.col()));
                }
                return;
            }

            List<Coordinate> neighbors = findUnvisited(maze, current, visited);
            if (!neighbors.isEmpty()) {
                Coordinate next = neighbors.get(new Random().nextInt(neighbors.size()));
                stack.push(next);
                visited[next.row()][next.col()] = true;
            } else {
                stack.pop();
            }
        }
    }

    /**
     * Returns a list of unvisited neighboring cells for the given coordinate in the maze.
     *
     * @param maze       the maze
     * @param coordinate the coordinate
     * @param visited    the array tracking visited cells
     * @return the list of unvisited neighboring cells
     */
    private List<Coordinate> findUnvisited(Maze maze, Coordinate coordinate, boolean[][] visited) {
        int row = coordinate.row();
        int col = coordinate.col();
        List<Coordinate> neighbors = new ArrayList<>();

        // Check the top neighboring cell
        if (row > 0 && !visited[row - 1][col] && maze.grid()[row - 1][col].getType() != Cell.Type.WALL) {
            neighbors.add(new Coordinate(row - 1, col));
        }
        // Check the bottom neighboring cell
        if (row < maze.height() - 1 && !visited[row + 1][col]
            && maze.grid()[row + 1][col].getType() != Cell.Type.WALL) {
            neighbors.add(new Coordinate(row + 1, col));
        }
        // Check the left adjacent cell
        if (col > 0 && !visited[row][col - 1] && maze.grid()[row][col - 1].getType() != Cell.Type.WALL) {
            neighbors.add(new Coordinate(row, col - 1));
        }
        // Check right adjacent cell
        if (col < maze.width() - 1 && !visited[row][col + 1] && maze.grid()[row][col + 1].getType() != Cell.Type.WALL) {
            neighbors.add(new Coordinate(row, col + 1));
        }

        return neighbors;
    }
}
