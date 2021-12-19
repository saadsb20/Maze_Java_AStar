package MazeGamemaster.GUI;

import MazeGamemaster.Entity.Agent;
import MazeGamemaster.SaveGame;
import MazeGamemaster.SearchAlgo.AstarSearchEngin;
import MazeGamemaster.Entity.Maze;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.*;

public class MazesPanel extends JPanel{
    JPanel contentPane ;
    Agent player;
    AstarSearchEngin currentSearchEngine = null;
    Thread thread;

    public void setDisplayPath(boolean displayPath) {
        this.displayPath = displayPath;
    }

    private boolean displayPath = false;
    private JPanel jPanel1 = new JPanel();
    public MazesPanel(int height, int width, int timeInSeconds, int nbrBonus, int nbrObstacle){
        playerMove();
        PaintPanel();
        this.player = new Agent(timeInSeconds);
        this.thread = new Thread(this.player);
        this.thread.start();
        currentSearchEngine = new AstarSearchEngin(height, width, nbrBonus, nbrObstacle);
    }

    public MazesPanel(Agent agent,int height, int width, int timeInSeconds, int nbrBonus, int nbrObstacle){
        playerMove();
        PaintPanel();
        this.player = new Agent(timeInSeconds);
        this.player.bonusVisited = agent.bonusVisited;
        this.player.obstacleVisited = agent.obstacleVisited;
        this.player.money = agent.money;
        this.player.startLoc = agent.startLoc;
        currentSearchEngine = new AstarSearchEngin(height, width, nbrBonus, nbrObstacle);
    }

    private void PaintPanel() {
        contentPane = new JPanel();
        this.setBorder(new TitledBorder(null, "GameGUI", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        this.setBounds(10, 10, 800, 795);
        contentPane.add(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (currentSearchEngine == null) return ;
        Maze maze = currentSearchEngine.getUpdatedMaze(player);
        SaveGame(maze);
        int width = maze.getWidth();
        int height = maze.getHeight();
        int nbrBonus = maze.getNbrBonus();
        int nbrObstacle = maze.getNbrObstacle();
        currentSearchEngine = new AstarSearchEngin(height,width, nbrBonus, nbrObstacle);

        int n2=width;
        int n=width+10;
        switch (width){
            case 21:
                n=width+10;
                n2 = width*50;
                break;
            case 41:
                n=width/2;
                n2 = width*24;
                break;
            case 61:
                n=width*10/44;
                n2 = width*22;
                break;
            case 81:
                n=width*10/80;
                n2 = width*14;
                break;
        }

        Graphics g1 = this.contentPane.getGraphics();
        BufferedImage image = new BufferedImage(n2+18, n2+18, BufferedImage.TYPE_INT_RGB);
        Graphics g2 = image.getGraphics();
        g2.setColor(Color.white);
        g2.fillRect(0, 0, n2-51, n2-51);
        g2.setColor(Color.black);
        maze.setValue(0,0 , (short) 10);
        for (int x=0; x<width; x++) {
            for (int y=0; y<height; y++) {
                short val = maze.getValue(x,y);

                if ( val == Maze.OBSTICLE) {
                    g2.setColor(Color.black);
                    g2.fillRect(6 + x * n, 3 + y * n, n, n);
                    g2.setColor(Color.black);
                    g2.drawRect(6 + x * n, 3 + y * n, n, n);
                } else if (val == Maze.START_LOC_VALUE) {
                    BufferedImage img =null;
                    try {
                        img = ImageIO.read( new File("./resource/joueur2.png" ));
                        g2.drawImage(img,(x)*n+6,(y)*n+4,n,n,this);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else if (val == Maze.GOAL_LOC_VALUE) {
                    g2.setColor(Color.red);
                    g2.fillRect(6 + x * n, 3 + y * n, n, n);

                } else if(val == 0) {

                }else if(val == 5) {
                    BufferedImage img =null;
                    try {
                        img = ImageIO.read( new File("./resource/coins.png" ));
                        g2.drawImage(img,(x)*n+6,(y)*n+4,n,n,this);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else if(val == 6) {
                    BufferedImage img =null;
                    try {
                        img = ImageIO.read( new File("./resource/enemies.png" ));
                        g2.drawImage(img,(x)*n+6,(y)*n+4,n,n,this);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else if(val ==10){
                    g2.setColor(Color.green);
                    g2.fillRect(6 + x * n, 3 + y * n, n, n);

                }
            }
        }

        if(displayPath) {
            g2.setColor(Color.black);
            Dimension[] path = currentSearchEngine.getPath();
            for (int i = 1; i < (path.length - 1); i++) {
                int x = path[i].width;
                int y = path[i].height;
                short val = maze.getValue(x, y);

                if(val == 5) {
                    BufferedImage img =null;
                    try {
                        g2.setColor(Color.GRAY);
                        g2.fillRect(6 + x * n, 3 + y * n, n, n);

                        img = ImageIO.read( new File("./resource/coins.png" ));
                        g2.drawImage(img,(x)*n+6,(y)*n+4,n,n,this);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }else if(val == 6) {
                    BufferedImage img =null;
                    try {
                        g2.setColor(Color.GRAY);
                        g2.fillRect(6 + x * n, 3 + y * n, n, n);

                        img = ImageIO.read( new File("./resource/enemies.png" ));
                        g2.drawImage(img,(x)*n+6,(y)*n+4,n,n,this);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    }else {
                            g2.setColor(Color.GRAY);
                            g2.fillRect(6 + x * n, 3 + y * n, n, n);
                            }
                }
        }
        g.drawImage(image, 20, 20, n2-51, n2-51, null);
    }


    public void SaveGame(Maze maze){
        try {
            FileOutputStream fos = new FileOutputStream("./save.dat");
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);

            SaveGame saveGame = new SaveGame();
            saveGame.setMaze(maze);

            saveGame.setMoney(player.getMoney());
            saveGame.setStartLoc(player.startLoc);
            saveGame.setBonusVisited(player.bonusVisited);
            saveGame.setObstacleVisited(player.obstacleVisited);
            oos.writeObject(saveGame);
            oos.close();

        }catch(IOException ex){
            ex.printStackTrace();
        }
    }


    public Agent getPlayer() {
        return player;
    }

    public AstarSearchEngin getCurrentSearchEngine() {
        return currentSearchEngine;
    }

    //movement
    public void playerMove(){
        bindKeyStrokeTo(WHEN_IN_FOCUSED_WINDOW, "down", KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //down
                player.moveDown(currentSearchEngine.getMaze());
                displayPath = false;
                repaint();
            }
        });
        bindKeyStrokeTo(WHEN_IN_FOCUSED_WINDOW, "up", KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
               //upMove
                player.moveUp(currentSearchEngine.getMaze());
                displayPath = false;
                repaint();
            }
        });
        bindKeyStrokeTo(WHEN_IN_FOCUSED_WINDOW, "left", KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //upLeft
                player.moveLeft(currentSearchEngine.getMaze());
                displayPath = false;
                repaint();
            }
        });
        bindKeyStrokeTo(WHEN_IN_FOCUSED_WINDOW, "right", KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //right
                player.moveRight(currentSearchEngine.getMaze());
                displayPath = false;
                repaint();
            }
        });
    }

    public void bindKeyStrokeTo(int condition, String name, KeyStroke keyStroke, Action action) {
        InputMap im = getInputMap(condition);
        ActionMap am = getActionMap();

        im.put(keyStroke, name);
        am.put(name, action);
    }
}

