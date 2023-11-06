package edu.project2.utility.solution;

import edu.project2.model.Cell;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BFSSolver implements Solver {

    /**
     * Solves the maze using the Shortest pathfinder(BFS) algorithm.
     *
     * @param maze  the maze to solve
     * @param start the start coordinate
     * @param end   the end coordinate
     * @return the list of coordinates representing the solution path
     */
    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        List<Coordinate> path = new ArrayList<>();
        Queue<Coordinate> queue = new LinkedList<>();
        Map<Coordinate, Coordinate> parentMap = new HashMap<>();
        boolean[][] visited = new boolean[maze.height()][maze.width()];

        if (!SolutionUtils.isWithinBounds(start, maze) || !SolutionUtils.isWithinBounds(end, maze)) {
            throw new IllegalArgumentException("Start or end coordinates are out of bounds.");
        }

        if (maze.grid()[start.row()][start.col()].getType() == Cell.Type.WALL
            || maze.grid()[end.row()][end.col()].getType() == Cell.Type.WALL) {
            return path;
        }

        queue.offer(start);
        visited[start.row()][start.col()] = true;

        bfs(maze, queue, visited, parentMap, end);

        Coordinate current = end;
        while (current != null) {
            path.add(0, current);
            current = parentMap.get(current);
        }

        return path;
    }

    private void bfs(
        Maze maze,
        Queue<Coordinate> queue,
        boolean[][] visited,
        Map<Coordinate, Coordinate> parentMap,
        Coordinate end
    ) {
        while (!queue.isEmpty()) {
            Coordinate current = queue.poll();

            if (current.equals(end)) {
                return;
            }

            List<Coordinate> neighbors = SolutionUtils.findNeighbors(maze, current, visited);
            for (Coordinate neighbor : neighbors) {
                queue.offer(neighbor);
                visited[neighbor.row()][neighbor.col()] = true;
                parentMap.put(neighbor, current);
            }
        }
    }
}
