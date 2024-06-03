package ui;

import db.bus.DataBUS;
import db.bus.LoginBUS;
import db.dao.LoginDAO;
import main.GamePanel;
import main.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import static main.Main.window;

public class LoginGUI extends JFrame implements ActionListener{
    JLabel LSignIn, LUsername, LPassword, LGmail;
    JButton BLogin, BForgotPassword, BContinueWithoutLogIn, BRegister;
    JTextField TFUsername, TFGmail;
    JPasswordField TFPassword;
    String inpgmail, inppassword;
    Font font = new Font("Tahoma", Font.BOLD, 15);
    ImageIcon Icon;
    LoginBUS logbus = new LoginBUS();
    DataBUS databus = new DataBUS();
    public static boolean isContinueWithoutLogin = false;
    public static String userID;

    public LoginGUI()
    {
        //----------------------------------------------------//
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Login");
        this.setLayout(null);
        this.setSize(550, 380);
        this.setResizable(false);
        //this.setIconImage(new ImageIcon("src/IMG/Login.png").getImage());
        //----------------------------------------------------//

        LSignIn = new JLabel("Sign in");
        LSignIn.setFont(new Font("Tahoma", Font.BOLD, 30));
        LSignIn.setForeground(new Color(0, 0, 0));
        LSignIn.setBounds(50, 20, 150, 50);

        LGmail = new JLabel("Gmail");
        LGmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
        LGmail.setForeground(new Color(0, 0, 0));
        LGmail.setBounds(50, 80, 150, 50);
        TFGmail = new JTextField();
        TFGmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
        TFGmail.setForeground(new Color(0, 0, 0));
        TFGmail.setBackground(new Color(255, 255, 255));
        TFGmail.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0, 170, 255)));
        TFGmail.setBounds(LGmail.getX(), LGmail.getY() + 45, 410, 30);

        LPassword = new JLabel("Password");
        LPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
        LPassword.setForeground(new Color(0, 0, 0));
        LPassword.setBounds(TFGmail.getX(), TFGmail.getY() + 35, 150, 50);
        TFPassword = new JPasswordField();
        TFPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
        TFPassword.setForeground(new Color(0, 0, 0));
        TFPassword.setBackground(new Color(255, 255, 255));
        TFPassword.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0, 170, 255)));
        TFPassword.setBounds(LPassword.getX(), LPassword.getY() + 45, 410, 30);

        BLogin = new JButton("Login");
        BLogin.setFont(font);
        BLogin.setForeground(Color.BLACK);
        //BLogin.setBackground(new Color(26, 140, 255));
        BLogin.setBackground(Color.WHITE);
        BLogin.setFocusable(false);
        BLogin.setBounds(TFPassword.getX() + 144, TFPassword.getY() + 55, 180, 40);
        BLogin.addActionListener(this);

        BForgotPassword = new JButton("Forgot password?");
        BForgotPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
        BForgotPassword.setForeground(Color.BLACK);
        //BForgotPassword.setBackground(new Color(26, 140, 255));
        BForgotPassword.setBackground(Color.WHITE);
        BForgotPassword.setFocusable(false);
        BForgotPassword.setBounds(BLogin.getX() - 140, BLogin.getY() + 50, 180, 20);
        BForgotPassword.setBorder(null);
        BForgotPassword.addActionListener(this);
        BForgotPassword.setFocusPainted(false);
        BForgotPassword.setOpaque(false);

        BRegister = new JButton("Register");
        BRegister.setFont(new Font("Tahoma", Font.PLAIN, 12));
        BRegister.setForeground(Color.BLACK);
        //BRegister.setBackground(new Color(26, 140, 255));
        BRegister.setBackground(Color.WHITE);
        BRegister.setFocusable(false);
        BRegister.setBounds(BLogin.getX() + 64, BLogin.getY() + 50, 50, 20);
        BRegister.setBorder(null);
        BRegister.addActionListener(this);
        BRegister.setFocusPainted(false);
        BRegister.setOpaque(false);

        BContinueWithoutLogIn = new JButton("Continue without login");
        BContinueWithoutLogIn.setFont(new Font("Tahoma", Font.PLAIN, 12));
        BContinueWithoutLogIn.setForeground(Color.BLACK);
        //BContinueWithoutLogIn.setBackground(new Color(26, 140, 255));
        BContinueWithoutLogIn.setBackground(Color.WHITE);
        BContinueWithoutLogIn.setFocusable(false);
        BContinueWithoutLogIn.setBounds(BLogin.getX() + 130, BLogin.getY() + 50, 180, 20);
        BContinueWithoutLogIn.setBorder(null);
        BContinueWithoutLogIn.addActionListener(this);
        BContinueWithoutLogIn.setFocusPainted(false);
        BContinueWithoutLogIn.setOpaque(false);


        this.add(LSignIn);
        this.add(LGmail);
        this.add(LPassword);
        this.add(TFGmail);
        this.add(TFPassword);
        this.add(BLogin);
        this.add(BForgotPassword);
        this.add(BContinueWithoutLogIn);
        this.add(BRegister);

        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.WHITE);
        this.setVisible(true);
        this.getRootPane().setDefaultButton(BLogin);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == BLogin)
        {
            //System.out.println("Login");

            inpgmail = TFGmail.getText();
            inppassword = TFPassword.getText();
            try {
                if(logbus.LoginCheck(inpgmail, inppassword))
                {
                    JOptionPane.showMessageDialog(null, "Welcome back " + inpgmail);
                    this.dispose();

                    window = new JFrame();
                    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    window.setResizable(false);
                    window.setTitle("Undertera");

                    // Set the icon
                    ImageIcon icon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/icon/icon.png")));
                    window.setIconImage(icon.getImage());


                    GamePanel gamepanel = new GamePanel();
                    window.add(gamepanel);

                    gamepanel.config.loadConfig();

                    window.pack();

                    window.setLocationRelativeTo(null);
                    window.setVisible(true);

                    gamepanel.setupGame();
                    gamepanel.startGameThread();
                    gamepanel.userID = logbus.GetUserID(inpgmail, inppassword);

                    databus.GetUserDataAndConfig(gamepanel.userID);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Login failed");
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        else if(e.getSource() == BRegister)
        {
            new RegisterGUI();
            this.dispose();
        }
        else if(e.getSource() == BForgotPassword)
        {
            new ForgotPasswordGUI();
            this.dispose();
        }
        else if(e.getSource() == BContinueWithoutLogIn)
        {
            this.dispose();
            window = new JFrame();
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setResizable(false);
            window.setTitle("Undertera");

            // Set the icon
            ImageIcon icon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/icon/icon.png")));
            window.setIconImage(icon.getImage());

            GamePanel gamepanel = new GamePanel();
            window.add(gamepanel);

            gamepanel.config.loadConfig();

            window.pack();

            window.setLocationRelativeTo(null);
            window.setVisible(true);

            gamepanel.setupGame();
            gamepanel.startGameThread();
        }
    }
}