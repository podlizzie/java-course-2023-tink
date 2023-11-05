package edu.project2;

import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import edu.project2.utility.generation.DFSGeneration;
import edu.project2.utility.solution.BFSSolver;
import edu.project2.utility.solution.DFSSolver;
import edu.project2.view.ConsoleRenderer;
import java.util.List;

public class Main {
    private Main() {

    }

    @SuppressWarnings({"RegexpSinglelineJava", "MagicNumber"})
    public static void main(String[] args) {
        int height = 10;
        int width = 30;
        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(8, 14);

        DFSGeneration generator = new DFSGeneration();
        Maze maze = generator.generate(height, width);
        ConsoleRenderer renderer = new ConsoleRenderer();
        System.out.println(renderer.render(maze));

        BFSSolver dfsSolver = new BFSSolver();
        List<Coordinate> path = dfsSolver.solve(maze, start, end);
        System.out.println(renderer.render(maze, path));

    }
}
