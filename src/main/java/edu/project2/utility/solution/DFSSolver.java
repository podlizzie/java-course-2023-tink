package edu.project2.utility.solution;

import edu.project2.model.Cell;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class DFSSolver implements Solver {

    /**
     * Solves the maze using the DFS algorithm.
     *
     * @param maze  the maze to solve
     * @param start the start coordinate
     * @param end   the end coordinate
     * @return the list of coordinates representing the solution path
     */
    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        List<Coordinate> path = new ArrayList<>();
        Stack<Coordinate> stack = new Stack<>();
        Map<Coordinate, Coordinate> parentMap = new HashMap<>();
        boolean[][] visited = new boolean[maze.height()][maze.width()];

        if (!MazeUtils.isWithinBounds(start, maze) || !MazeUtils.isWithinBounds(end, maze)) {
            throw new IllegalArgumentException("Start or end coordinates are out of bounds.");
        }

        if (maze.grid()[start.row()][start.col()].getType() == Cell.Type.WALL
            || maze.grid()[end.row()][end.col()].getType() == Cell.Type.WALL) {
            return path;
        }

        stack.push(start);
        visited[start.row()][start.col()] = true;

        dfs(maze, stack, visited, parentMap, end);

        Coordinate current = end;
        while (current != null) {
            path.add(0, current);
            current = parentMap.get(current);
        }

        return path;
    }

    private void dfs(
        Maze maze,
        Stack<Coordinate> stack,
        boolean[][] visited,
        Map<Coordinate, Coordinate> parentMap,
        Coordinate end
    ) {
        while (!stack.isEmpty()) {
            Coordinate current = stack.pop();

            if (current.equals(end)) {
                return;
            }

            List<Coordinate> neighbors = MazeUtils.findUnvisited(maze, current, visited);
            for (Coordinate neighbor : neighbors) {
                if (!visited[neighbor.row()][neighbor.col()]) {
                    stack.push(neighbor);
                    visited[neighbor.row()][neighbor.col()] = true;
                    parentMap.put(neighbor, current);
                }
            }
        }
    }
}
