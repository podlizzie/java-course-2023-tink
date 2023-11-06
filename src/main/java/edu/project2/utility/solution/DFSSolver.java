package edu.project2.utility.solution;

import edu.project2.model.Cell;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map<Coordinate, Coordinate> parentMap = new HashMap<>();
        boolean[][] visited = new boolean[maze.height()][maze.width()];

        if (!SolutionUtils.isWithinBounds(start, maze) || !SolutionUtils.isWithinBounds(end, maze)) {
            throw new IllegalArgumentException("Start or end coordinates are out of bounds.");
        }

        if (maze.grid()[start.row()][start.col()].getType() == Cell.Type.WALL
            || maze.grid()[end.row()][end.col()].getType() == Cell.Type.WALL) {
            return path;
        }

        visited[start.row()][start.col()] = true;
        dfs(maze, start, end, visited, path);

        Coordinate current = end;
        while (current != null) {
            path.add(0, current);
            current = parentMap.get(current);
        }
        Collections.reverse(path);

        return path;
    }

    private void dfs(
        Maze maze,
        Coordinate current,
        Coordinate end,
        boolean[][] visited,
        List<Coordinate> path
    ) {
        if (current.equals(end)) {
            path.add(current);
            return;
        }

        visited[current.row()][current.col()] = true;

        List<Coordinate> neighbors = SolutionUtils.findNeighbors(maze, current, visited);
        for (Coordinate neighbor : neighbors) {
            dfs(maze, neighbor, end, visited, path);
        }

        if (path.contains(end)) {
            path.add(current);
        }
    }

}
