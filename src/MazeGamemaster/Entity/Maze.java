package MazeGamemaster.Entity;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;


public class Maze extends JFrame {
    public static short OBSTICLE = -1;
    public static short START_LOC_VALUE = -2;
    public static short GOAL_LOC_VALUE = -3;
    private int width ;
    private int height ;
    private int nbrBonus ;
    private int nbrObstacle ;
    public Agent startLoc;
    public Dimension goalLoc  = new Dimension();
    private final short[][] maze;

    public Maze(int width, int height, int nbrBonus, int nbrObstacle) {
        this.width = width;
        this.height = height;
        this.nbrBonus = nbrBonus;
        this.nbrObstacle = nbrObstacle;
        
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

        goalLoc.width = width - 1;
        goalLoc.height = height - 1;
        maze[width][height] = GOAL_LOC_VALUE;

        addBonus(nbrBonus);
        addObstacle(nbrObstacle);
    }


    public void addBonus(int amount){
        Random location = new Random();
        location.setSeed(1L);

        while (amount > 0) {
            int randX = location.nextInt(width - 1);
            int randY = location.nextInt(width - 1);

            if (getValue(randX, randY) == 0){
                setValue(randX, randY,(short)5);
                amount--;
            }
        }
    }

    public void addObstacle(int amount){
        Random location = new Random();
        location.setSeed(2L);

        while (amount > 0) {
            int randX = location.nextInt(width - 1);
            int randY = location.nextInt(width - 1);

            if (getValue(randX, randY) == 0){
                setValue(randX, randY,(short)6);
                amount--;
            }
        }
    }

    synchronized public short getValue(int x, int y) { return maze[x+1][y+1]; }
    synchronized public void setValue(int x, int y, short value) { maze[x+1][y+1] = value; repaint();}
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public int getNbrBonus() { return nbrBonus; }
    public int getNbrObstacle() { return nbrObstacle; }
}


