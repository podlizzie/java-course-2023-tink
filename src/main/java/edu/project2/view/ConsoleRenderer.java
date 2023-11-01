package edu.project2.view;

import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import java.util.List;

public class ConsoleRenderer implements Renderer {

    /**
     * Renders the maze without a solution path.
     *
     * @param maze the maze to render
     * @return the rendered maze as a string
     */
    @Override
    public String render(Maze maze) {
        return null;
    }

    /**
     * Renders the maze with a solution path.
     *
     * @param maze the maze to render
     * @param path the solution path
     * @return the rendered maze with the solution path as a string
     */
    @Override
    public String render(Maze maze, List<Coordinate> path) {
        return null;
    }
}
