package ui;

import java.awt.*;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import Entity.EmployeeEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import Util.HibernateUtil;
import java.awt.event.*;
import java.util.Random;

public class AddEmployee extends JFrame implements ActionListener {

    Random ran = new Random();
    int number = ran.nextInt(999999);

    JTextField tfname, tffname, tfaddress, tfphone, tfaadhar, tfemail, tfsalary, tfdesignation;
    JDateChooser dcdob;
    JComboBox<String> cbeducation;
    JLabel lblempId;
    JButton add, back;

    public AddEmployee() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Add Employee Detail");
        heading.setBounds(320, 30, 500, 50);
        heading.setFont(new Font("SAN_SERIF", Font.BOLD, 25));
        add(heading);

        String[] labels = {
            "Name", "Father's Name", "Date of Birth", "Salary", "Address",
            "Phone", "Email", "Highest Education", "Designation", "Aadhar Number", "Employee ID"
        };
        int[][] bounds = {{50,150}, {400,150}, {50,200}, {400,200}, {50,250}, {400,250},
                          {50,300}, {400,300}, {50,350}, {400,350}, {50,400}};
        Component[] fields = new Component[11];

        tfname = new JTextField(); fields[0] = tfname;
        tffname = new JTextField(); fields[1] = tffname;
        dcdob = new JDateChooser(); fields[2] = dcdob;
        tfsalary = new JTextField(); fields[3] = tfsalary;
        tfaddress = new JTextField(); fields[4] = tfaddress;
        tfphone = new JTextField(); fields[5] = tfphone;
        tfemail = new JTextField(); fields[6] = tfemail;

        String[] courses = {"BBA", "BCA", "BA", "BSC", "B.COM", "BTech", "MBA", "MCA", "MA", "MTech", "MSC", "PHD"};
        cbeducation = new JComboBox<>(courses); cbeducation.setBackground(Color.WHITE); fields[7] = cbeducation;

        tfdesignation = new JTextField(); fields[8] = tfdesignation;
        tfaadhar = new JTextField(); fields[9] = tfaadhar;
        lblempId = new JLabel(String.valueOf(number)); fields[10] = lblempId;

        Font labelFont = new Font("serif", Font.PLAIN, 20);
        for (int i = 0; i < labels.length; i++) {
            JLabel lbl = new JLabel(labels[i]);
            lbl.setBounds(bounds[i][0], bounds[i][1], 150, 30);
            lbl.setFont(labelFont);
            add(lbl);

            Component field = fields[i];
            if (field instanceof JTextField || field instanceof JDateChooser || field instanceof JComboBox) {
                field.setBounds(bounds[i][0] + 150, bounds[i][1], 150, 30);
                add(field);
            } else if (field instanceof JLabel) {
                ((JLabel) field).setBounds(bounds[i][0] + 150, bounds[i][1], 150, 30);
                ((JLabel) field).setFont(labelFont);
                add(field);
            }
        }

        add = new JButton("Add Details");
        add.setBounds(250, 550, 150, 40);
        add.addActionListener(this);
        add.setBackground(Color.BLACK);
        add.setForeground(Color.WHITE);
        add(add);

        back = new JButton("Back");
        back.setBounds(450, 550, 150, 40);
        back.addActionListener(this);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        add(back);

        setSize(900, 700);
        setLocation(300, 50);
        setVisible(true);
    }


    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == add) {
            try {
                String name = tfname.getText();
                String fname = tffname.getText();
                String dob = ((JTextField) dcdob.getDateEditor().getUiComponent()).getText();
                String salary = tfsalary.getText();
                String address = tfaddress.getText();
                String phone = tfphone.getText();
                String email = tfemail.getText();
                String education = (String) cbeducation.getSelectedItem();
                String designation = tfdesignation.getText();
                String aadhar = tfaadhar.getText();
                String empId = lblempId.getText();
                
                // NAME VALIDATION
                if (name.isEmpty() || !name.matches("[a-zA-Z\\s]+") || name.length() > 30) {
                    JOptionPane.showMessageDialog(null, "Invalid Name. Only letters allowed and max 30 characters.");
                    return;
                }

                if (fname.isEmpty() || !fname.matches("[a-zA-Z\\s]+") || fname.length() > 30) {
                    JOptionPane.showMessageDialog(null, "Invalid Father's Name. Only letters allowed and max 30 characters.");
                    return;
                }



                // VALIDATIONS
                if (!phone.matches("[6-9][0-9]{9}")) {
                    JOptionPane.showMessageDialog(null, "Invalid phone number. Must be 10 digits and start with 6-9.");
                    return;
                }

                else if (!aadhar.matches("\\d{12}")) {
                    JOptionPane.showMessageDialog(null, "Invalid Aadhar number. Must be exactly 12 digits.");
                    return;
                }

                else if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                    JOptionPane.showMessageDialog(null, "Invalid email address.");
                    return;
                }

                // Continue only if all validations pass
                EmployeeEntity emp = new EmployeeEntity();
                emp.setName(name);
                emp.setFname(fname);
                emp.setDob(dob);
                emp.setSalary(salary);
                emp.setAddress(address);
                emp.setPhone(phone);
                emp.setEmail(email);
                emp.setEducation(education);
                emp.setDesignation(designation);
                emp.setAadhar(aadhar);
                emp.setEmpId(empId);

                try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                    Transaction tx = session.beginTransaction();
                    session.save(emp);
                    tx.commit();
                    JOptionPane.showMessageDialog(null, "Details added successfully");
                    setVisible(false);
                    new Home(); // Or your desired navigation
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error saving employee. Please try again.");
            }
        } else {
            setVisible(false);
            new Home(); // Or your desired navigation
        }
    }


    public static void main(String[] args) {
        new AddEmployee();
    }
}
