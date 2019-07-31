package main;

import maze.Maze;

import java.io.File;
import java.io.IOException;


public class Main {

    public static void main (String[] args) throws IOException {

        File file;
        try {

            String fullFilePath = args[0];

             file = new File(fullFilePath);
             System.out.println("File name :" + file.getName());


        } catch (Exception e) {

            System.out.println("Please enter full file path in program arguments");
            e.printStackTrace();
            return;

        }
            Maze maze = new Maze();
            maze.scanMazeFromFile(file);

    }
}
