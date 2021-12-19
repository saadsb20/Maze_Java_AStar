package MazeGamemaster;

import MazeGamemaster.Entity.Agent;
import MazeGamemaster.Entity.Maze;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class SaveGame implements Serializable {
    private Maze maze;
    private  Dimension StartLoc = new Dimension();
    private  int Money;

    private  ArrayList<Dimension> bonusVisited;
    private  ArrayList<Dimension> obstacleVisited;


    public ArrayList<Dimension> getBonusVisited() {
        return bonusVisited;
    }

    public void setBonusVisited(ArrayList<Dimension> bonusVisited) {
        this.bonusVisited = bonusVisited;
    }

    public ArrayList<Dimension> getObstacleVisited() {
        return obstacleVisited;
    }

    public void setObstacleVisited(ArrayList<Dimension> obstacleVisited) {
        this.obstacleVisited = obstacleVisited;
    }

    public  int getMoney() {
        return Money;
    }

    public  void setMoney(int money) {
        Money = money;
    }

    public SaveGame() {
    }

    public  Dimension getStartLoc() {
        return StartLoc;
    }

    public  void setStartLoc(Dimension startLoc) {
        StartLoc = startLoc;
    }

    public Maze getMaze() {
        return maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }
}
