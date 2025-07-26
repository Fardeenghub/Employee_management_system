package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Splash extends JFrame implements ActionListener {

    JLabel heading;
    Timer timer;

    Splash() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        heading = new JLabel("EMPLOYEE MANAGEMENT SYSTEM");
        heading.setBounds(80, 30, 1200, 60);
        heading.setFont(new Font("serif", Font.PLAIN, 60));
        heading.setForeground(Color.RED);
        add(heading);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/front.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1100, 700, Image.SCALE_DEFAULT);
        JLabel image = new JLabel(new ImageIcon(i2));
        image.setBounds(50, 100, 1050, 500);
        add(image);

        JButton clickhere = new JButton("CLICK HERE TO CONTINUE");
        clickhere.setBounds(400, 400, 300, 70);
        clickhere.setBackground(Color.BLACK);
        clickhere.setForeground(Color.WHITE);
        clickhere.addActionListener(this);
        image.add(clickhere);

        setSize(1170, 650);
        setLocation(200, 50);
        setVisible(true);

        // Blinking using Swing Timer (non-blocking)
        timer = new Timer(500, e -> heading.setVisible(!heading.isVisible()));
        timer.start();
    }

    public void actionPerformed(ActionEvent ae) {
        timer.stop();
        setVisible(false);
        new Login();
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> new Splash());
    }
}
