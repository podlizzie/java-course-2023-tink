package edu.hw2.task3;

import edu.hw2.task3.manager.DefaultConnectionManager;
//import edu.hw2.task3.manager.FaultyConnectionManager;

public class Main {
    private Main() {

    }

    public static void main(String[] args) {
        //test
        PopularCommandExecutor commandExecutor =
            new PopularCommandExecutor(new DefaultConnectionManager(), 2);
        commandExecutor.updatePackages();
    }
}
