package maze;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Maze {
    private char [][] maze = null;
    private int width;
    private int height;
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private Scanner input;
    private String message;

    public void scanMazeFromFile(File file) throws IOException {
        input = new Scanner(file);
        width = input.nextInt();
        height = input.nextInt();
        startX = input.nextInt();
        startY = input.nextInt();
        endX = input.nextInt();
        endY = input.nextInt();
        input.nextLine();
        populateMazeArray();
        formatMaze();
        solveMaze(startY,startX);
        printMaze();
    }

    public void populateMazeArray() {
        maze = new char [height][width];
        for(int row = 0; row < height; row++) {
            int index = 0;
            String line = input.nextLine();
            for (int col = 0; col < width; col++) {
                maze[row][col] = line.charAt(index);
                index = index + 2;
            }
        }
        input.close();
    }

    public void formatMaze() {

        maze[startY][startX] = 'S';
        maze[endY][endX] = 'E';

        for(int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if(maze[row][col] == '1'){

                    maze[row][col] = '#';
                }

                if(maze[row][col] == '0'){

                    maze[row][col] = ' ';
                }

            }
        }
    }

    public boolean solveMaze(int a, int b) {
        int firstIndex = 0;
        int lastIndex = width - 1;
        int firstRow = 0;
        int lastRow = height - 1;
        message = "Exit not Found!";
        if (a >= 0 && b >= 0 && a < height && b < width) {
            int row = a;
            int col = b;

            if (maze[row][col] == '#') {
                return false;
            }

            if (maze[row][col] == 'E') {
                message = "Exit Found!";
                return true;
            }
            if (maze[row][col] == 'X') {
                return false;
            }

            maze[row][col] = 'X';

            //South or Vertical wrapping
            if ((solveMaze(row + 1, col))) {
                return true;
            }
            else if (row == lastRow && (solveMaze(firstRow, col))){
                return true;
            }

            //West or Horizontal wrapping
            if ((solveMaze(row, col - 1))) {
                return true;
            }else if (col == firstIndex && (solveMaze(row, lastIndex))){
                return true;
            }

            //East or Horizontal wrapping
            if ((solveMaze(row, col + 1))) {
                return true;
            }else if (col == lastIndex && (solveMaze(row, firstIndex))){
                return true;
            }

            //North or Vertical wrapping
            if ((solveMaze(row - 1, col))) {
                return true;
            }else if (row == firstRow && (solveMaze(lastRow, col))){
                return true;
            }


            maze[row][col] = ' ';
            return false;
        }
        return false;
    }


    private void printMaze(){
        maze[startY][startX] = 'S';
        for(int j=0; j < maze.length; j++){
            System.out.println(maze[j]);
        }
        System.out.println(message);
    }
}
