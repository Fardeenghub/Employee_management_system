package ui;

import Entity.EmployeeEntity;
import Util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UpdateEmployee extends JFrame implements ActionListener {

    JTextField tfFname, tfAddress, tfPhone, tfEmail, tfAadhar, tfEducation, tfDesignation, tfSalary;
    JButton updateBtn, backBtn;
    String empId;
    EmployeeEntity employee;

    UpdateEmployee(String empId) {
        this.empId = empId;

        setLayout(null);
        setTitle("Update Employee");

        JLabel lblHeading = new JLabel("Update Employee Details");
        lblHeading.setBounds(100, 10, 400, 30);
        lblHeading.setFont(new Font("serif", Font.BOLD, 20));
        add(lblHeading);

        JLabel lblFname = new JLabel("Name");
        lblFname.setBounds(50, 60, 100, 30);
        add(lblFname);

        tfFname = new JTextField();
        tfFname.setBounds(200, 60, 200, 30);
        add(tfFname);

        JLabel lblAddress = new JLabel("Address");
        lblAddress.setBounds(50, 100, 100, 30);
        add(lblAddress);

        tfAddress = new JTextField();
        tfAddress.setBounds(200, 100, 200, 30);
        add(tfAddress);

        JLabel lblPhone = new JLabel("Phone");
        lblPhone.setBounds(50, 140, 100, 30);
        add(lblPhone);

        tfPhone = new JTextField();
        tfPhone.setBounds(200, 140, 200, 30);
        add(tfPhone);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(50, 180, 100, 30);
        add(lblEmail);

        tfEmail = new JTextField();
        tfEmail.setBounds(200, 180, 200, 30);
        add(tfEmail);

        JLabel lblAadhar = new JLabel("Aadhar");
        lblAadhar.setBounds(50, 220, 100, 30);
        add(lblAadhar);

        tfAadhar = new JTextField();
        tfAadhar.setBounds(200, 220, 200, 30);
        add(tfAadhar);

        JLabel lblEducation = new JLabel("Education");
        lblEducation.setBounds(50, 260, 100, 30);
        add(lblEducation);

        tfEducation = new JTextField();
        tfEducation.setBounds(200, 260, 200, 30);
        add(tfEducation);

        JLabel lblDesignation = new JLabel("Designation");
        lblDesignation.setBounds(50, 300, 100, 30);
        add(lblDesignation);

        tfDesignation = new JTextField();
        tfDesignation.setBounds(200, 300, 200, 30);
        add(tfDesignation);

        JLabel lblSalary = new JLabel("Salary");
        lblSalary.setBounds(50, 340, 100, 30);
        add(lblSalary);

        tfSalary = new JTextField();
        tfSalary.setBounds(200, 340, 200, 30);
        add(tfSalary);

        updateBtn = new JButton("Update");
        updateBtn.setBounds(100, 400, 100, 30);
        updateBtn.addActionListener(this);
        add(updateBtn);

        backBtn = new JButton("Back");
        backBtn.setBounds(250, 400, 100, 30);
        backBtn.addActionListener(this);
        add(backBtn);

        // Load employee from DB
        loadEmployee(empId);

        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    void loadEmployee(String empId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        employee = session.get(EmployeeEntity.class, empId);
        session.close();

        if (employee == null) {
            JOptionPane.showMessageDialog(null, "Employee not found!");
            dispose();
            return;
        }

        // Set existing data
        tfFname.setText(employee.getFname());
        tfAddress.setText(employee.getAddress());
        tfPhone.setText(employee.getPhone());
        tfEmail.setText(employee.getEmail());
        tfAadhar.setText(employee.getAadhar());
        tfEducation.setText(employee.getEducation());
        tfDesignation.setText(employee.getDesignation());
        tfSalary.setText(employee.getSalary());
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == updateBtn) {
            if (employee == null) {
                JOptionPane.showMessageDialog(null, "No employee loaded.");
                return;
            }

            // Update fields
            employee.setFname(tfFname.getText());
            employee.setAddress(tfAddress.getText());
            employee.setPhone(tfPhone.getText());
            employee.setEmail(tfEmail.getText());
            employee.setAadhar(tfAadhar.getText());
            employee.setEducation(tfEducation.getText());
            employee.setDesignation(tfDesignation.getText());
            employee.setSalary(tfSalary.getText());

            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.update(employee);
            tx.commit();
            session.close();

            JOptionPane.showMessageDialog(null, "Employee Updated Successfully");
            setVisible(false);
            new ViewEmployee();

        } else if (ae.getSource() == backBtn) {
            setVisible(false);
            new ViewEmployee();
        }
    }

    public static void main(String[] args) {
        new UpdateEmployee("101"); // Replace with dynamic empId when integrating
    }
}
