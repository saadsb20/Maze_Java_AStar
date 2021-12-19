package MazeGamemaster;

import MazeGamemaster.Entity.Agent;
import MazeGamemaster.GUI.LevelFrame;
import MazeGamemaster.GUI.PartieGUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class MainGame extends JFrame {
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {


            public void run() {

                try {
                    for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                        if ("Nimbus".equals(info.getName())) {
                            javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
                            break;
                        }
                    }
                } catch (ClassNotFoundException ex) {
                    java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                    java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }

                try {
                    MainGame frame = new MainGame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MainGame() {


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        panel.setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(29, 11, 364, 214);
        panel.add(panel_1);
        panel_1.setLayout(null);

        JButton btnNewButton = new JButton("Nouvelle Partie");
        btnNewButton.setBounds(124, 60, 118, 25);
        panel_1.add(btnNewButton);


        JButton btnNewButton2 = new JButton(" Charger une Partie");
        btnNewButton2.setBounds(124, 105, 118, 25);
        panel_1.add(btnNewButton2);

        JButton btnNewButton3 = new JButton("Quitter");
        btnNewButton3.setBounds(124, 150, 118, 25);
        panel_1.add(btnNewButton3);


        JLabel lblNewLabel = new JLabel("!! MazeGame !!");
        lblNewLabel.setFont(new Font("Andalus", Font.BOLD, 18));
        lblNewLabel.setBounds(119, 11, 123, 37);
        panel_1.add(lblNewLabel);

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LevelFrame();
            }
        });

        btnNewButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    FileInputStream fis = new FileInputStream("./save.dat");
                    BufferedInputStream bis = new BufferedInputStream(fis);
                    ObjectInputStream ois = new ObjectInputStream(bis);

                    SaveGame saveGame = (SaveGame) ois.readObject();
                    Agent agent = null;
                    agent.startLoc = saveGame.getStartLoc();
                    agent.bonusVisited = saveGame.getBonusVisited();
                    agent.obstacleVisited = saveGame.getObstacleVisited();
                    agent.money = saveGame.getMoney();

                    dispose();
                    new PartieGUI(agent,saveGame.getMaze().getHeight(),saveGame.getMaze().getWidth(), 120, saveGame.getMaze().getNbrBonus(), saveGame.getMaze().getNbrObstacle());

                }catch(IOException | ClassNotFoundException ex){
                    ex.printStackTrace();
                }
            }
        });
        btnNewButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                close();
                dispose();
            }
        });
        this.setResizable(false);
        this.setVisible(true);

    }

    public void close() {
        WindowEvent winClosingEvent = new WindowEvent( this, WindowEvent.WINDOW_CLOSING );
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent( winClosingEvent );
    }
}
