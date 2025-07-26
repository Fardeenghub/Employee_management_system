package ui;

import javax.swing.*;
import LoginDao.LoginDao;
import java.awt.*;
import java.awt.event.*;


public class Login extends JFrame implements ActionListener {
	JTextField tfusername;
    JPasswordField tfpassword;
    JButton login;
 

    Login() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(40, 20, 100, 30);
        add(lblusername);

        tfusername = new JTextField();
        tfusername.setBounds(150, 20, 150, 30);
        add(tfusername);

        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(40, 70, 100, 30);
        add(lblpassword);

        tfpassword = new JPasswordField();
        tfpassword.setBounds(150, 70, 150, 30);
        add(tfpassword);

        login = new JButton("LOGIN");
        login.setBounds(150, 140, 150, 30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        add(login);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        JLabel image = new JLabel(new ImageIcon(i2));
        image.setBounds(350, 0, 200, 200);
        add(image);

        setSize(600, 300);
        setLocation(450, 200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == login) {
            String user = tfusername.getText();
            String pass = new String(tfpassword.getPassword());

            LoginDao loginDao = new LoginDao();
            boolean isValid = loginDao.validateUser(user, pass);

            if (isValid) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                setVisible(false);
                 new Home(); // Uncomment once Home.java is ready
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password.");
            }

        } 
    }


    public static void main(String[] args) {
        new Login();
    }
}
