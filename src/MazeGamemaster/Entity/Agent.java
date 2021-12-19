package MazeGamemaster.Entity;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import static java.time.temporal.ChronoUnit.SECONDS;

/**
 * A class that creates all the elements necessary for a player
 * to traverse the maze.
 */
public class Agent implements Runnable {

	private LocalDateTime startTime;
	private int timeReserved;
	private static int money = 0;
	public static Dimension startLoc = new Dimension();
	private Maze maze;

	public Agent(int timeReserved){
		this.timeReserved = timeReserved;
		this.startTime = LocalDateTime.now();
	}

    public static void moveLeft(Maze maze){
		if((maze.getValue(startLoc.width-1, startLoc.height) != -1)){
			maze.setValue(startLoc.width, startLoc.height, (short) 0);
			if(maze.getValue(startLoc.width-=1, startLoc.height) == (short)-3 ) endGame();
			else if(maze.getValue(startLoc.width, startLoc.height) == (short) 5)money++;
			else if(maze.getValue(startLoc.width, startLoc.height) == (short) 6)money--;
			else maze.setValue(startLoc.width, startLoc.height, (short)-2);
		}
		else maze.setValue(startLoc.width, startLoc.height, (short)-2);
		System.out.println("You got " + money);
	}

	public static void moveRight(Maze maze){
		if((maze.getValue(startLoc.width+1, startLoc.height) != -1) ){
			maze.setValue(startLoc.width, startLoc.height, (short) 0);
			if(maze.getValue(startLoc.width+=1, startLoc.height) == (short)-3 ) endGame();
			else if(maze.getValue(startLoc.width, startLoc.height) == (short) 5)money++;
			else if(maze.getValue(startLoc.width, startLoc.height) == (short) 6)money--;
			else maze.setValue(startLoc.width, startLoc.height, (short)-2);
		}
		else maze.setValue(startLoc.width, startLoc.height, (short)-2);
		System.out.println("You got " + money);
	}

	public static void moveDown(Maze maze){
		if((maze.getValue(startLoc.width, startLoc.height+1) != -1) ){
			maze.setValue(startLoc.width, startLoc.height, (short) 0);
			if(maze.getValue(startLoc.width, startLoc.height+=1) == (short)-3 ) endGame();
			else if(maze.getValue(startLoc.width, startLoc.height) == (short) 5)money++;
			else if(maze.getValue(startLoc.width, startLoc.height) == (short) 6)money--;
			else maze.setValue(startLoc.width, startLoc.height, (short)-2);
		}
		else maze.setValue(startLoc.width, startLoc.height, (short)-2);
		System.out.println("You got " + money);
	}

	public static void moveUp(Maze maze){
		if((maze.getValue(startLoc.width, startLoc.height-1) != -1) ){
			maze.setValue(startLoc.width, startLoc.height, (short) 0);
			if(maze.getValue(startLoc.width, startLoc.height-=1) == (short)-3 ) endGame();
			else if(maze.getValue(startLoc.width, startLoc.height) == (short) 5)money++;
			else if(maze.getValue(startLoc.width, startLoc.height) == (short) 6)money--;
			else maze.setValue(startLoc.width, startLoc.height, (short)-2);
			maze.setValue(1,1,(short)0);
		}
		else maze.setValue(startLoc.width, startLoc.height, (short)-2);
		maze.setValue(1,1,(short)0);
		System.out.println("You got " + money);
	}

	public static void endGame(){
		 MazeFrame(20, money);
	}

	public static void MazeFrame(int level, int money){
		JPanel p3 = new JPanel(new BorderLayout());
		JFrame frame2 = new JFrame();

		JLabel textLabel = new JLabel("<html>Congratulations!<br>You got " + money + " coin(s)!</html>", JLabel.CENTER);
		textLabel.setFont(new Font("Verdana", Font.BOLD, 32));

		frame2.add(p3, BorderLayout.SOUTH);
		frame2.setBackground(Color.green);
		frame2.add(textLabel, BorderLayout.CENTER);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.setSize(900, 850);
		frame2.setLocationRelativeTo(null);
		frame2.setVisible(true);
	}

	@Override
	public void run() {
		while (true){

			this.getCountDown();
			if (LocalDateTime.now().isAfter(this.startTime.plusSeconds(this.timeReserved))){
				endGame();
				break;
			}
		}
	}

	public String getCountDown(){
		Long uptime  = SECONDS.between(LocalDateTime.now(), this.startTime.plusSeconds(this.timeReserved));

		Long hours = TimeUnit.MILLISECONDS.toHours(uptime);
		uptime -= TimeUnit.HOURS.toMillis(hours);

		Long minutes = TimeUnit.SECONDS.toMinutes(uptime);
		uptime -= TimeUnit.MINUTES.toMillis(minutes);

		Long seconds = TimeUnit.SECONDS.toSeconds(uptime);
		return (hours < 9 ? "0" : "") + hours + ":" + (minutes < 9 ? "0" : "") + minutes + ":" + (seconds < 9 ? "0" : "") + seconds;
	}
}
