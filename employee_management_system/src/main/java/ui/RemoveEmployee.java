package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import Entity.EmployeeEntity;
import Util.HibernateUtil;

public class RemoveEmployee extends JFrame implements ActionListener {

    Choice cEmpId;
    JButton delete, back;
    JLabel lblname, lblphone, lblemail;

    RemoveEmployee() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel labelempId = new JLabel("Employee Id");
        labelempId.setBounds(50, 50, 100, 30);
        add(labelempId);

        cEmpId = new Choice();
        cEmpId.setBounds(200, 50, 150, 30);
        add(cEmpId);

        JLabel labelname = new JLabel("Name");
        labelname.setBounds(50, 100, 100, 30);
        add(labelname);

        lblname = new JLabel();
        lblname.setBounds(200, 100, 150, 30);
        add(lblname);

        JLabel labelphone = new JLabel("Phone");
        labelphone.setBounds(50, 150, 100, 30);
        add(labelphone);

        lblphone = new JLabel();
        lblphone.setBounds(200, 150, 150, 30);
        add(lblphone);

        JLabel labelemail = new JLabel("Email");
        labelemail.setBounds(50, 200, 100, 30);
        add(labelemail);

        lblemail = new JLabel();
        lblemail.setBounds(200, 200, 150, 30);
        add(lblemail);

        loadEmployeeIds();
        loadEmployeeDetails(cEmpId.getSelectedItem());

        cEmpId.addItemListener(e -> loadEmployeeDetails(cEmpId.getSelectedItem()));

        delete = new JButton("Delete");
        delete.setBounds(80, 300, 100, 30);
        delete.setBackground(Color.BLACK);
        delete.setForeground(Color.WHITE);
        delete.addActionListener(this);
        add(delete);

        back = new JButton("Back");
        back.setBounds(220, 300, 100, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/delete.png"));
        Image i2 = i1.getImage().getScaledInstance(600, 400, Image.SCALE_DEFAULT);
        JLabel image = new JLabel(new ImageIcon(i2));
        image.setBounds(350, 0, 600, 400);
        add(image);

        setSize(1000, 400);
        setLocation(300, 150);
        setVisible(true);
    }

    private void loadEmployeeIds() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        	Query<String> query = session.createQuery("SELECT e.empId FROM EmployeeEntity e", String.class);
            List<String> empIds = query.getResultList();
            for (String id : empIds) {
                cEmpId.add(id);
            }
        }
    }

    private void loadEmployeeDetails(String empId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        	EmployeeEntity emp = session.get(EmployeeEntity.class, empId);
            if (emp != null) {
                lblname.setText(emp.getName());
                lblphone.setText(emp.getPhone());
                lblemail.setText(emp.getEmail());
            }
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == delete) {
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                Transaction tx = session.beginTransaction();
                EmployeeEntity emp = session.get(EmployeeEntity.class, cEmpId.getSelectedItem());
                if (emp != null) {
                    session.delete(emp);
                    tx.commit();
                    JOptionPane.showMessageDialog(null, "Employee Information Deleted Successfully");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            setVisible(false);
            new Home();
        } else {
            setVisible(false);
            new Home();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RemoveEmployee());
    }
}
