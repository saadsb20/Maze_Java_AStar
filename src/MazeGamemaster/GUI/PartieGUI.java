package MazeGamemaster.GUI;

import MazeGamemaster.Entity.Agent;
import MazeGamemaster.MainGame;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class PartieGUI extends JFrame {
    private  JPanel PanelButton = new JPanel();
    private JPanel contentPane = new JPanel();
    private MazesPanel panel;
    private JPanel ButtonsPanel = new JPanel();
    private JButton displayPathButton= new JButton("Aide ?");



    public PartieGUI(int height, int width, int timeInSeconds, int nbrBonus, int nbrObstacle){
        panel = new MazesPanel(height, width, timeInSeconds, nbrBonus, nbrObstacle);
        JPanelPaint();
        setTitle("MAZE GAME -LSI2021-");
//                    p.actionDilay();
        setVisible(true);
    }



    private void JPanelPaint() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 850);
        getContentPane().setLayout(null);
        //
        panel.setLayout(null);
        contentPane.add(panel);
        //
        ButtonsPanel.setBounds(810,10,165,795);
        ButtonsPanel.setBorder(new TitledBorder(null, "Game Options", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        ButtonsPanel.setLayout(null);
        contentPane.setLayout(null);

        displayPathButton.setBounds(30, 50, 100, 23);
        ButtonsPanel.add(displayPathButton);

        displayPathButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.setDisplayPath(true);
                repaint();
            }
        });
        //button2
        JButton B1 = new JButton("Quitter Partie");
        B1.setBounds(30, 500, 100, 23);
        ButtonsPanel.add(B1);
        B1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                panel.thread.stop();
                panel.player.resetValeu();
                dispose();

                new MainGame();
            }
        });

        JLabel ScorLabel = new JLabel("Score ");
        ScorLabel.setBounds(10, 80, 145, 35);
        ScorLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        ScorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ButtonsPanel.add(ScorLabel);


        JLabel TimeLabel = new JLabel("Time ");
        TimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        TimeLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        TimeLabel.setBounds(10, 140, 145, 35);
        ButtonsPanel.add(TimeLabel);

        JLabel scorelbl = new JLabel("Score :"+panel.player.getMoney());
        scorelbl.setHorizontalAlignment(SwingConstants.CENTER);
        scorelbl.setBounds(10, 100, 145, 35);
        scorelbl.setFont(new Font("Times New Roman", Font.BOLD, 20));
        ButtonsPanel.add(scorelbl);

        JLabel timelbl = new JLabel("Time :");
        timelbl.setBounds(10, 160, 145, 35);
        timelbl.setHorizontalAlignment(SwingConstants.CENTER);
        timelbl.setFont(new Font("Times New Roman", Font.BOLD, 20));
        ButtonsPanel.add(timelbl);

        ActionListener l1 =new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                scorelbl.setText(""+panel.player.getMoney());

            }
        };

        Timer t1 = new Timer(100,l1);
        t1.start();
        ActionListener l2 =new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                timelbl.setText( panel.player.getCountDown());
            }
        };

        Timer t2 = new Timer(100,l2);
        t2.start();





        contentPane.add(ButtonsPanel);
        this.setContentPane(contentPane);
        this.setLocationRelativeTo(null);



    }
    public void close() {
        WindowEvent winClosingEvent = new WindowEvent( this, WindowEvent.WINDOW_CLOSING );
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent( winClosingEvent );
    }




   /* public static void main(String[] args) {


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
                    PartieGUI p = new PartieGUI();
                    p.setTitle("MAZE GAME -LSI-");
//                    p.actionDilay();
                    p.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });



    }*/
}
