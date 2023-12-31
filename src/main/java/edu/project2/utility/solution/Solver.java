package edu.project2.utility.solution;

import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import java.util.List;

public interface Solver {
    /**
     * Solves the maze using start and end coordinates.
     *
     * @param maze  the maze to solve
     * @param start the start coordinate
     * @param end   the end coordinate
     * @return the list of coordinates representing the solution path
     */
    List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end);
}
