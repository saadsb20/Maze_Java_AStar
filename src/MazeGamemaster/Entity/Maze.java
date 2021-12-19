package MazeGamemaster.Entity;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 * Class MazeGamemaster.Entity.Maze - private class for representing search space as a two-dimensional maze
 */
public class Maze extends JFrame {
    public static short OBSTICLE = -1;
    public static short START_LOC_VALUE = -2;
    public static short GOAL_LOC_VALUE = -3;
    private int width ;
    private int height ;
//    public Dimension startLoc = new Dimension();
    public Agent startLoc;
    public Dimension goalLoc  = new Dimension();
    /**
     * The maze (or search space) data is stored as a short integer rather than
     * as a boolean so that bread-first style searches can use the array to store
     * search depth. A value of -1 indicates a barrier in the maze.
     */
    private final short[][] maze;
    public Maze(int width, int height) {
        System.out.println("New maze of size " + width + " by " + height);
        this.width = width;
        this.height = height;
        
        maze = new short[width+2][height+2];
        for (int i=0; i<width+2; i++) {
            for (int j=0; j<height+2; j++) {
                maze[i][j] = 0;
            }
        }
        for (int i=0; i<height+2; i++) {
            maze[0][i] = maze[width+1][i] = OBSTICLE;
        }
        for (int i=0; i<width+2; i++) {
            maze[i][0] = maze[i][height+1] = OBSTICLE;
        }
        /**
         * Randomize the maze by putting up arbitray obsticals
         */
        File myObj = new File("./resource/LABY_"+height+"x"+width+".txt/");
        Scanner myReader = null;
        try {
            myReader = new Scanner(myObj);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int line = 1;
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            for (int i = 0; i < data.length(); i++) {
                maze[i+1][line] =(data.charAt(i)) == 32 ? (short) 0 :OBSTICLE;
            }
            line++;
        }
        myReader.close();
        
        /**
         * Specify the starting location
         */

        /**
         * Specify the goal location
         */
        goalLoc.width = width - 1;
        goalLoc.height = height - 1;
        maze[width][height] = GOAL_LOC_VALUE;

        addBonus(width-1);
        addObstacle(width-1);
        afficherConsole();
    }


    public void addBonus(int amount){
        Random location = new Random();
        location.setSeed(1);
        System.out.println(location);

        for (int i = 0; i < amount; i++) {
            int xPos = 0, yPos = 0;
            while ((xPos == 0) || (yPos == 0)) {
                int randX = location.nextInt(amount);
                int randY = location.nextInt(amount);

                if (((randX % 2) != 0) && (randX != 0 || randX != amount)) xPos = randX;
                if (((randY % 2) != 0) && (randY != 0 || randY != amount)) yPos = randY;

                if ((maze[xPos][yPos] == Maze.START_LOC_VALUE) || maze[xPos][yPos] == Maze.GOAL_LOC_VALUE) {
                    xPos =0;
                    yPos = 0;
                }
            }
            setValue(xPos,yPos,(short)5);
        }
    }

    public void addObstacle(int amount){
        Random location2 = new Random();
        location2.setSeed(2);

        for (int i = 0; i < amount; i++) {
            int xPos = 0, yPos = 0;
            while ((xPos == 0) || (yPos == 0)) {
                int randX = location2.nextInt(amount);
                int randY = location2.nextInt(amount);

                if (((randX % 2) != 0) && (randX != 0 || randX != amount)) xPos = randX;
                if (((randY % 2) != 0) && (randY != 0 || randY != amount)) yPos = randY;

                if ((maze[xPos][yPos] == Maze.START_LOC_VALUE) || maze[xPos][yPos] == Maze.GOAL_LOC_VALUE) {
                    xPos =0;
                    yPos = 0;
                }
            }
            setValue(xPos,yPos,(short)6);
        }
    }

    public void afficherConsole(){
        for (int i = 0; i < this.maze.length; i++) {
            for (int j = 0; j < this.maze[0].length; j++) {
                System.out.print((this.maze[j][i]>=0?" " + this.maze[j][i]: this.maze[j][i]) + " ");
            }
            System.out.println();
        }
    }
    synchronized public short getValue(int x, int y) { return maze[x+1][y+1]; }
    synchronized public void setValue(int x, int y, short value) { maze[x+1][y+1] = value; repaint();}
    public int getWidth() { return width; }
    public int getHeight() { return height; }
}


