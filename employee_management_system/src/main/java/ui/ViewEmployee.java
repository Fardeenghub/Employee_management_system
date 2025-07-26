package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import org.hibernate.Session;
import Entity.EmployeeEntity;
import Util.HibernateUtil;

public class ViewEmployee extends JFrame implements ActionListener {

    JTable table;
    Choice cemployeeId;
    JButton search, print, update, back;

    ViewEmployee() {

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel searchlbl = new JLabel("Search by Employee Id");
        searchlbl.setBounds(20, 20, 150, 20);
        add(searchlbl);

        cemployeeId = new Choice();
        cemployeeId.setBounds(180, 20, 150, 20);
        add(cemployeeId);

        // Load all employee IDs
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<EmployeeEntity> employees = session.createQuery("from EmployeeEntity", EmployeeEntity.class).list();
            for (EmployeeEntity emp : employees) {
                cemployeeId.add(emp.getEmpId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        table = new JTable();
        loadTableData(null);  // Load all initially

        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 100, 900, 600);
        add(jsp);

        search = new JButton("Search");
        search.setBounds(20, 70, 80, 20);
        search.addActionListener(this);
        add(search);

        print = new JButton("Print");
        print.setBounds(120, 70, 80, 20);
        print.addActionListener(this);
        add(print);

        update = new JButton("Update");
        update.setBounds(220, 70, 80, 20);
        update.addActionListener(this);
        add(update);

        back = new JButton("Back");
        back.setBounds(320, 70, 80, 20);
        back.addActionListener(this);
        add(back);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/view.jpg"));
        Image i2 = i1.getImage().getScaledInstance(915, 700, Image.SCALE_SMOOTH); 
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 915, 700);
        add(image);
       

        setSize(915, 700);
        setLocation(300, 100);
        setVisible(true);
    }

    private void loadTableData(String empId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<EmployeeEntity> employees;
            if (empId != null) {
                employees = session.createQuery("from EmployeeEntity where empId = :id", EmployeeEntity.class)
                                   .setParameter("id", empId)
                                   .list();
            } else {
                employees = session.createQuery("from EmployeeEntity", EmployeeEntity.class).list();
            }

            // Table Model
            String[] columns = {"Emp ID", "Name", "FName", "DOB", "Salary", "Address", "Phone", "Email", "Education", "Designation", "Aadhar"};
            DefaultTableModel model = new DefaultTableModel(columns, 0);

            for (EmployeeEntity emp : employees) {
                model.addRow(new Object[]{
                    emp.getEmpId(), emp.getName(), emp.getFname(), emp.getDob(), emp.getSalary(),
                    emp.getAddress(), emp.getPhone(), emp.getEmail(), emp.getEducation(),
                    emp.getDesignation(), emp.getAadhar()
                });
            }

            table.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == search) {
            loadTableData(cemployeeId.getSelectedItem());
        } else if (ae.getSource() == print) {
            try {
                table.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == update) {
            setVisible(false);
            new UpdateEmployee(cemployeeId.getSelectedItem());
        } else {
            setVisible(false);
            new Home();
        }
    }

    public static void main(String[] args) {
        new ViewEmployee();
    }
}
