package edu.hw9.task3.utility.generation;

import edu.project2.model.Maze;

public interface Generator {
    /**
     * Generates a maze using height and width.
     *
     * @param height the height of the maze
     * @param width  the width of the maze
     * @return the generated maze
     */
    Maze generate(int height, int width);
}
