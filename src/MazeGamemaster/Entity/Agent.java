package MazeGamemaster.Entity;

import MazeGamemaster.MainGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static java.time.temporal.ChronoUnit.SECONDS;

public class Agent implements Runnable {

    private LocalDateTime startTime;
    private int timeReserved;
    public static int money = 0;
    public static Dimension startLoc = new Dimension();
    public static ArrayList<Dimension> bonusVisited;
    public static ArrayList<Dimension> obstacleVisited;
    private static boolean timeout = false;

	public boolean isTimeout() {
		return timeout;
	}

	public Agent(int timeReserved) {
        this.timeReserved = timeReserved;
        this.startTime = LocalDateTime.now();
        bonusVisited = new ArrayList<Dimension>();
        obstacleVisited = new ArrayList<Dimension>();
    }

    public static void moveLeft(Maze maze) {
        if ((maze.getValue(startLoc.width - 1, startLoc.height) != -1)) {
            maze.setValue(startLoc.width, startLoc.height, (short) 0);
            if (maze.getValue(startLoc.width -= 1, startLoc.height) == (short) -3) endGame();
            else if (money == 0 && maze.getValue(startLoc.width, startLoc.height) == (short) 6) endGame();
            else if (maze.getValue(startLoc.width, startLoc.height) == (short) 5) {
                bonusVisited.add(new Dimension(startLoc.width, startLoc.height));
                money++;
            } else if (maze.getValue(startLoc.width, startLoc.height) == (short) 6) {
                obstacleVisited.add(new Dimension(startLoc.width, startLoc.height));
                money--;
            } else maze.setValue(startLoc.width, startLoc.height, (short) -2);
        } else maze.setValue(startLoc.width, startLoc.height, (short) -2);
    }

    public static void moveRight(Maze maze) {
        if ((maze.getValue(startLoc.width + 1, startLoc.height) != -1)) {
            maze.setValue(startLoc.width, startLoc.height, (short) 0);
            if (maze.getValue(startLoc.width += 1, startLoc.height) == (short) -3) endGame();
            else if (money == 0 && maze.getValue(startLoc.width, startLoc.height) == (short) 6) endGame();
            else if (maze.getValue(startLoc.width, startLoc.height) == (short) 5) {
                bonusVisited.add(new Dimension(startLoc.width, startLoc.height));
                money++;
            } else if (maze.getValue(startLoc.width, startLoc.height) == (short) 6) {
                obstacleVisited.add(new Dimension(startLoc.width, startLoc.height));
                money--;
            } else maze.setValue(startLoc.width, startLoc.height, (short) -2);
        } else maze.setValue(startLoc.width, startLoc.height, (short) -2);
    }

    public static void moveDown(Maze maze) {
        if ((maze.getValue(startLoc.width, startLoc.height + 1) != -1)) {
            maze.setValue(startLoc.width, startLoc.height, (short) 0);
            if (maze.getValue(startLoc.width, startLoc.height += 1) == (short) -3) endGame();
            else if (money == 0 && maze.getValue(startLoc.width, startLoc.height) == (short) 6) endGame();
            else if (maze.getValue(startLoc.width, startLoc.height) == (short) 5) {
                bonusVisited.add(new Dimension(startLoc.width, startLoc.height));
                money++;
            } else if (maze.getValue(startLoc.width, startLoc.height) == (short) 6) {
                obstacleVisited.add(new Dimension(startLoc.width, startLoc.height));
                money--;
            } else maze.setValue(startLoc.width, startLoc.height, (short) -2);
        } else maze.setValue(startLoc.width, startLoc.height, (short) -2);
    }

    public static void moveUp(Maze maze) {
        if ((maze.getValue(startLoc.width, startLoc.height - 1) != -1)) {
            maze.setValue(startLoc.width, startLoc.height, (short) 0);
            if (maze.getValue(startLoc.width, startLoc.height -= 1) == (short) -3) endGame();
            else if (money == 0 && maze.getValue(startLoc.width, startLoc.height) == (short) 6) endGame();
            else if (maze.getValue(startLoc.width, startLoc.height) == (short) 5) {
                bonusVisited.add(new Dimension(startLoc.width, startLoc.height));
                money++;
            } else if (maze.getValue(startLoc.width, startLoc.height) == (short) 6) {
                obstacleVisited.add(new Dimension(startLoc.width, startLoc.height));
                money--;
            } else maze.setValue(startLoc.width, startLoc.height, (short) -2);
            maze.setValue(1, 1, (short) 0);
        } else maze.setValue(startLoc.width, startLoc.height, (short) -2);
        maze.setValue(1, 1, (short) 0);
    }

    public static void endGame() {
        MazeFrame(20, money);
    }

    public static void MazeFrame(int level, int money) {


            JFrame frame2 = new JFrame();

            frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame2.setBackground(Color.green);
            frame2.getContentPane().setLayout(null);

            JPanel panel = new JPanel();
            panel.setBounds(10, 11, 414, 167);
            frame2.getContentPane().add(panel);
            panel.setLayout(null);
		if (money == 0) {
			JLabel textLabel = new JLabel("<html>You lost, you got " + money + "  coin(s)!</html>", JLabel.CENTER);
            textLabel.setBounds(57, 29, 300, 93);
            panel.add(textLabel);
            textLabel.setFont(new Font("Verdana", Font.BOLD, 32));
		}else if(timeout){
			JLabel textLabel = new JLabel("<html>You lost, Timeout!</html>", JLabel.CENTER);
			textLabel.setBounds(57, 29, 300, 93);
			panel.add(textLabel);
			textLabel.setFont(new Font("Verdana", Font.BOLD, 32));
		}else{
			JLabel textLabel = new JLabel("<html>Congratulations, you got "+ money + " coin(s)!</html>", JLabel.CENTER);
			textLabel.setBounds(57, 29, 300, 93);
			panel.add(textLabel);
			textLabel.setFont(new Font("Verdana", Font.BOLD, 32));
		}

        JButton btnNewButton_1 = new JButton("Quitter");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WindowEvent winClosingEvent = new WindowEvent(frame2, WindowEvent.WINDOW_CLOSING);
                Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
                new MainGame();
            }
        });
        btnNewButton_1.setBounds(181, 207, 89, 23);
        frame2.getContentPane().add(btnNewButton_1);

        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setBounds(100, 100, 450, 300);
        frame2.setLocationRelativeTo(null);
        frame2.setVisible(true);
    }

    @Override
    public void run() {
		boolean time = true;
        while (time) {
            this.getCountDown();
            if (LocalDateTime.now().isAfter(this.startTime.plusSeconds(this.timeReserved))) {
            	timeout = true;
            	time = false;
                endGame();
                break;
            }
        }
    }

    public String getCountDown() {
        Long uptime = SECONDS.between(LocalDateTime.now(), this.startTime.plusSeconds(this.timeReserved));

        Long hours = TimeUnit.SECONDS.toHours(uptime);
        uptime -= TimeUnit.HOURS.toSeconds(hours);

        Long minutes = TimeUnit.SECONDS.toMinutes(uptime);
        uptime -= TimeUnit.MINUTES.toSeconds(minutes);

        Long seconds = TimeUnit.SECONDS.toSeconds(uptime);
        return (hours < 9 ? "0" : "") + hours + ":" + (minutes < 9 ? "0" : "") + minutes + ":" + (seconds < 9 ? "0" : "") + seconds;
    }

    public static int getMoney() {
        return money;
    }

    public static ArrayList<Dimension> getBonusVisited() {
        return bonusVisited;
    }

    public static ArrayList<Dimension> getObstacleVisited() {
        return obstacleVisited;
    }

    public void resetValeu() {
        startLoc.width = 0;
        startLoc.height = 0;
        money = 0;
    }
}
