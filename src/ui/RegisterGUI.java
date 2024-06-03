package ui;
import db.bus.LoginBUS;
import validate.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterGUI extends JFrame implements ActionListener{
    JLabel LUsername, LPassword, LConfirmPassword, LRegister, LEmail;
    JButton BRegister, BBack;
    JTextField TFUsername, TFEmail;
    JPasswordField TFPassword, TFConfirmPassword;
    ImageIcon Icon;
    LoginBUS logbus = new LoginBUS();

    //--------------------  Regex -------------------------//

    //Regular Expression
    String regex = "^(.+)@(.+)$";
    Pattern pattern = Pattern.compile(regex);

    //----------------------------------------------------//
    public RegisterGUI()
    {
        //----------------------------------------------------//
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Register");
        this.setLayout(null);
        this.setSize(550, 485);
        this.setResizable(false);
        //----------------------------------------------------//
        //Icon = new ImageIcon("src/IMG/Back.png");
        BBack = new JButton("Back");
        BBack.setIcon(Icon);
        BBack.setFont(new Font("Tahoma", Font.PLAIN, 13));
        BBack.setForeground(Color.BLACK);
        BBack.setBackground(Color.WHITE);
        BBack.setBounds(450, 10, 60, 30);
        BBack.setFocusable(false);
        BBack.setBorder(null);
        BBack.addActionListener(this);

        LRegister = new JLabel("Register");
        LRegister.setFont(new Font("Tahoma", Font.BOLD, 25));
        LRegister.setForeground(new Color(0, 0, 0));
        LRegister.setBounds(40, 10, 150, 50);

        LUsername = new JLabel("Username");
        LUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
        LUsername.setForeground(new Color(0, 0, 0));
        LUsername.setBounds(40, 50, 150, 50);
        TFUsername = new JTextField();
        TFUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
        TFUsername.setForeground(new Color(0, 0, 0));
        TFUsername.setBackground(new Color(255, 255, 255));
        TFUsername.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0, 170, 255)));
        TFUsername.setBounds(LUsername.getX(), LUsername.getY() + 45, 260, 30);

        LEmail = new JLabel("Gmail");
        LEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
        LEmail.setForeground(new Color(0, 0, 0));
        LEmail.setBounds(TFUsername.getX(), TFUsername.getY() + 35, 150, 50);
        TFEmail = new JTextField();
        TFEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
        TFEmail.setForeground(new Color(0, 0, 0));
        TFEmail.setBackground(new Color(255, 255, 255));
        TFEmail.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0, 170, 255)));
        TFEmail.setBounds(LEmail.getX(), LEmail.getY() + 45, 260, 30);

        LPassword = new JLabel("Password");
        LPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
        LPassword.setForeground(new Color(0, 0, 0));
        LPassword.setBounds(TFEmail.getX(), TFEmail.getY() + 35, 150, 50);
        TFPassword = new JPasswordField();
        TFPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
        TFPassword.setForeground(new Color(0, 0, 0));
        TFPassword.setBackground(new Color(255, 255, 255));
        TFPassword.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0, 170, 255)));
        TFPassword.setBounds(LPassword.getX(), LPassword.getY() + 45, 260, 30);

        LConfirmPassword = new JLabel("Confirm Password");
        LConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
        LConfirmPassword.setForeground(new Color(0, 0, 0));
        LConfirmPassword.setBounds(TFPassword.getX(), TFPassword.getY() + 35, 150, 50);
        TFConfirmPassword = new JPasswordField();
        TFConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
        TFConfirmPassword.setForeground(new Color(0, 0, 0));
        TFConfirmPassword.setBackground(new Color(255, 255, 255));
        TFConfirmPassword.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0, 170, 255)));
        TFConfirmPassword.setBounds(LConfirmPassword.getX(), LConfirmPassword.getY() + 45, 260, 30);

        BRegister = new JButton("Register");
        BRegister.setFont(new Font("Tahoma", Font.BOLD, 12));
        BRegister.setForeground(Color.BLACK);
        BRegister.setBackground(Color.WHITE);
        BRegister.setFocusable(false);
        BRegister.setBounds(TFConfirmPassword.getX() + 144, TFConfirmPassword.getY() + 45, 180, 40);
        BRegister.addActionListener(this);

        //----------------------------------------------------//
        this.add(BBack);
        this.add(LRegister);
        this.add(LUsername);
        this.add(LEmail);
        this.add(TFEmail);
        this.add(TFUsername);
        this.add(LPassword);
        this.add(TFPassword);
        this.add(LConfirmPassword);
        this.add(TFConfirmPassword);
        this.add(BRegister);
        //----------------------------------------------------//

        //----------------------------------------------------//
        this.getContentPane().setBackground(Color.WHITE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.getRootPane().setDefaultButton(BRegister);
        //----------------------------------------------------//
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == BBack)
        {
            new LoginGUI();
            this.dispose();
        }
        if(e.getSource() == BRegister)
        {
            Matcher matcher = pattern.matcher(TFEmail.getText());
            if(!matcher.matches())
            {
                JOptionPane.showMessageDialog(null, "Invalid Email", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                SecureRandom secureRandom = new SecureRandom();
                long randomLong = secureRandom.nextLong();
                randomLong = Math.abs(randomLong); // Ensure positive value
                String randomIdString = String.valueOf(randomLong).substring(0, 12);

                String username = TFUsername.getText();
                String password = TFPassword.getText();
                String confirm_password = TFConfirmPassword.getText();
                if(!Objects.equals(password, confirm_password))
                {
                    JOptionPane.showMessageDialog(null, "Mismatched password", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    try {
                        boolean ok = logbus.AddAccount(randomIdString, TFEmail.getText(), username, password, "", "");
                        if(ok)
                        {
                            JOptionPane.showMessageDialog(null, "Register successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                            new LoginGUI();
                            this.dispose();
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "An error occurred", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            }
        }
    }
}
