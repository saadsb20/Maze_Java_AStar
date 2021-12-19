package MazeGamemaster.GUI;

import MazeGamemaster.Entity.Agent;
import MazeGamemaster.MainGame;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
        setResizable(false);
        setVisible(true);
    }

    public PartieGUI(Agent agent, int height, int width, int timeInSeconds, int nbrBonus, int nbrObstacle){
        panel = new MazesPanel(agent,height, width, timeInSeconds, nbrBonus, nbrObstacle);
        JPanelPaint();
        setTitle("MAZE GAME -LSI2021-");
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

        displayPathButton.setBounds(30, 336, 100, 23);
        ButtonsPanel.add(displayPathButton);

        displayPathButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.setDisplayPath(true);
                repaint();
            }
        });
        //button2
        JButton B1 = new JButton("Quitter Partie");
        B1.setBounds(30, 748, 100, 23);
        B1.setFont(new Font("Times New Roman", Font.BOLD, 12));
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
        ScorLabel.setBounds(10, 360, 145, 35);
        ScorLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        ScorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ButtonsPanel.add(ScorLabel);


        JLabel TimeLabel = new JLabel("Time ");
        TimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        TimeLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        TimeLabel.setBounds(10, 410, 145, 35);
        ButtonsPanel.add(TimeLabel);

        JLabel scorelbl = new JLabel(""+panel.player.getMoney());
        scorelbl.setHorizontalAlignment(SwingConstants.CENTER);
        scorelbl.setBounds(10, 385, 145, 35);
        scorelbl.setFont(new Font("Times New Roman", Font.BOLD, 20));
        ButtonsPanel.add(scorelbl);

        JLabel timelbl = new JLabel("00:00:00");
        timelbl.setBounds(10, 435, 145, 35);
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
        JButton btnSave = new JButton("Enregistrer la partie");
        btnSave.setBounds(30, 711, 100, 23);
        btnSave.setFont(new Font("Times New Roman", Font.BOLD, 12));
        ButtonsPanel.add(btnSave);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Guide", TitledBorder.CENTER, TitledBorder.TOP, null, null));
        panel.setBounds(10, 40, 145, 260);
        ButtonsPanel.add(panel);
        panel.setLayout(null);

        JLabel enimisL = new JLabel("Ennemi");
        enimisL.setFont(new Font("Times New Roman", Font.BOLD, 11));
        enimisL.setBounds(57, 26, 78, 24);
        panel.add(enimisL);

        JPanel enimeImage = new JPanel();
        enimeImage.setBackground(Color.white);
        enimeImage.setBounds(10, 26, 30, 30);
        //img
        try{
        BufferedImage image = ImageIO.read(new File("./resource/enemies.png"));
        JLabel label = new JLabel(new ImageIcon(image.getScaledInstance(30, 30,Image.SCALE_DEFAULT)));
        enimeImage.add(label);
        }catch (IOException e){
            e.printStackTrace();
        }

        panel.add(enimeImage);

        JPanel joueurimg = new JPanel();
        joueurimg.setBackground(Color.white);
        joueurimg.setBounds(10, 71, 30, 30);
        try{
            BufferedImage image = ImageIO.read(new File("./resource/joueur2.png"));
            JLabel label = new JLabel(new ImageIcon(image.getScaledInstance(30, 30,Image.SCALE_DEFAULT)));
            joueurimg.add(label);
        }catch (IOException e){
            e.printStackTrace();
        }
        panel.add(joueurimg);

        JLabel joueurlb = new JLabel("Joueur");
        joueurlb.setFont(new Font("Times New Roman", Font.BOLD, 11));
        joueurlb.setBounds(57, 71, 78, 24);
        panel.add(joueurlb);

        JPanel coinsimg = new JPanel();
        coinsimg.setBackground(Color.white);
        coinsimg.setBounds(10, 162, 30, 30);
        try{
            BufferedImage image = ImageIO.read(new File("./resource/coins.png"));
            JLabel label = new JLabel(new ImageIcon(image.getScaledInstance(30, 30,Image.SCALE_DEFAULT)));
            coinsimg.add(label);
        }catch (IOException e){
            e.printStackTrace();
        }
        panel.add(coinsimg);

        JLabel lblNewLabel_2 = new JLabel("New label");
        lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 11));
        lblNewLabel_2.setBounds(57, 162, 78, 24);
        panel.add(lblNewLabel_2);

        JLabel lblNewLabel_2_1 = new JLabel("Point de d\u00E9part");
        lblNewLabel_2_1.setFont(new Font("Times New Roman", Font.BOLD, 11));
        lblNewLabel_2_1.setBounds(57, 204, 78, 24);
        panel.add(lblNewLabel_2_1);

        JPanel startimg = new JPanel();
        startimg.setBackground(Color.green);
        startimg.setBounds(10, 204, 30, 30);
        panel.add(startimg);

        JLabel lblNewLabel_2_2 = new JLabel("Point final");
        lblNewLabel_2_2.setFont(new Font("Times New Roman", Font.BOLD, 11));
        lblNewLabel_2_2.setBounds(57, 120, 78, 24);
        panel.add(lblNewLabel_2_2);

        JPanel endimg = new JPanel();
        endimg.setBackground(Color.red);
        endimg.setBounds(10, 120, 30, 30);
        panel.add(endimg);

        this.setContentPane(contentPane);
        this.setLocationRelativeTo(null);
    }
    public void close() {
        WindowEvent winClosingEvent = new WindowEvent( this, WindowEvent.WINDOW_CLOSING );
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent( winClosingEvent );
    }
}
