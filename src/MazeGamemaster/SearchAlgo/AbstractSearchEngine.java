package MazeGamemaster.SearchAlgo;

import MazeGamemaster.Entity.Maze;

import java.awt.*;

public class AbstractSearchEngine {

    protected Maze maze;

    protected Dimension[] searchPath = null;
    protected int pathCount;
    protected int maxDepth;
    protected Dimension startLoc, goalLoc, currentLoc;
    protected boolean isSearching = true;
    
    AbstractSearchEngine(int width, int height, int nbrBonus, int nbrObstacle) {
        maze = new Maze(width, height, nbrBonus, nbrObstacle);
        initSearch();
    }

    public Maze getMaze() {
        return maze;
    }

    protected void initSearch() {
        if (searchPath == null) {
            searchPath = new Dimension[1000];
            for (int i = 0; i < 1000; i++) {
                searchPath[i] = new Dimension();
            }
        }
        pathCount = 0;
        startLoc = maze.startLoc.startLoc;
        currentLoc = startLoc;
        goalLoc = maze.goalLoc;
        searchPath[pathCount++] = currentLoc;
    }

    protected boolean equals(Dimension d1, Dimension d2) {
        return d1.getWidth() == d2.getWidth() && d1.getHeight() == d2.getHeight();
       
    }

    public Dimension[] getPath() {
        Dimension[] ret = new Dimension[maxDepth];
        for (int i = 0; i < maxDepth; i++) {
            ret[i] = searchPath[i];
        }
        return ret;
    }

    protected Dimension[] getPossibleMoves(Dimension loc) {
        Dimension tempMoves[] = new Dimension[8];
        for (int i = 0; i < tempMoves.length; i++) {
            tempMoves[i]=null;
        }
        int x = loc.width;
        int y = loc.height;
        int num = 0;
        if (maze.getValue(x - 1, y) == 0 || maze.getValue(x - 1, y)==5 ||maze.getValue(x - 1, y)==6 || maze.getValue(x - 1, y) == Maze.GOAL_LOC_VALUE) {
            tempMoves[num++] = new Dimension(x - 1, y);
        }
        if (maze.getValue(x + 1, y) == 0 || maze.getValue(x + 1, y) == 5 || maze.getValue(x + 1, y) == 6  || maze.getValue(x + 1, y) == Maze.GOAL_LOC_VALUE) {
            tempMoves[num++] = new Dimension(x + 1, y);
        }
        if (maze.getValue(x, y - 1) == 0  || maze.getValue(x, y - 1) == 5 || maze.getValue(x, y - 1) == 6  || maze.getValue(x, y - 1) == Maze.GOAL_LOC_VALUE) {
            tempMoves[num++] = new Dimension(x, y - 1);
        }
        if (maze.getValue(x, y + 1) == 0 || maze.getValue(x, y + 1) == 5 || maze.getValue(x, y + 1) == 6 || maze.getValue(x, y + 1) == Maze.GOAL_LOC_VALUE) {
            tempMoves[num++] = new Dimension(x, y + 1);
        }

        return tempMoves;
    }
}
