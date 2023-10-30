package edu.project1;

import edu.project1.view.ConsoleHangman;

public final class Main {

    private Main() {
    }

    public static void main(String[] args) {
        ConsoleHangman game = new ConsoleHangman();
        game.run();
    }
}
