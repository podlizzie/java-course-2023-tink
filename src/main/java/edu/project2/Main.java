package edu.project2;

import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import edu.project2.utility.generation.DFSGeneration;
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
        Coordinate coordinate1 = new Coordinate(0, 0);
        Coordinate coordinate2 = new Coordinate(8, 147);

        DFSGeneration generator = new DFSGeneration();
        Maze maze = generator.generate(height, width);
        ConsoleRenderer renderer = new ConsoleRenderer();
        System.out.println(renderer.render(maze));

        DFSSolver dfsSolver = new DFSSolver();
        List<Coordinate> path = dfsSolver.solve(maze, coordinate1, coordinate2);
        System.out.println(renderer.render(maze, path));

    }
}
