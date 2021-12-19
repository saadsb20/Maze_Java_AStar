package MazeGamemaster;

import MazeGamemaster.GUI.LevelFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGame extends JFrame {

//    public MainGame(){
//
//        this.setCursor(null);
//        this.setDefaultCloseOperation(3);
//
//        setResizable(false);
//    }
//
//    public static void main(String[] args) {
//        MainGame m = new MainGame();
//        JPanel jPanel1 = new JPanel();
//        m.setTitle("MainGame");
//        m.setContentPane(jPanel1);
//        m.getContentPane().setLayout(null);
//        jPanel1.setBackground(Color.white);
//        jPanel1.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);
//        jPanel1.setDoubleBuffered(false);
//        jPanel1.setRequestFocusEnabled(false);
//        jPanel1.setLayout(null);
//        m.setSize(1020, 850);
//        m.setLocationRelativeTo(null);
//        m.setVisible(true);
//    }


    private JPanel contentPane;

    /**
     * Launch the application.
     */
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

    /**
     * Create the frame.
     */
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
        btnNewButton.setBounds(124, 59, 118, 23);
        panel_1.add(btnNewButton);

        JButton btnOption = new JButton("Option2\r\n");
        btnOption.setBounds(124, 97, 118, 23);
        panel_1.add(btnOption);

        JButton btnOption_1 = new JButton("Option3");
        btnOption_1.setBounds(124, 131, 118, 23);
        panel_1.add(btnOption_1);

        JButton btnOption_2 = new JButton("Option4");
        btnOption_2.setBounds(124, 165, 118, 23);
        panel_1.add(btnOption_2);

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
        this.setVisible(true);

    }
}
