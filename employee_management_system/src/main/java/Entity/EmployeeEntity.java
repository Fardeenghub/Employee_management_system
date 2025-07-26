package Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employee")
public class EmployeeEntity {

    @Id
    private String empId;

    private String name;
    private String fname;
    private String dob;
    private String salary;
    private String address;
    private String phone;
    private String email;
    private String education;
    private String designation;
    private String aadhar;

    // ----- Getters and Setters -----
    public String getEmpId() {
        return empId;
    }
    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getFname() {
        return fname;
    }
    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getDob() {
        return dob;
    }
    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getSalary() {
        return salary;
    }
    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getEducation() {
        return education;
    }
    public void setEducation(String education) {
        this.education = education;
    }

    public String getDesignation() {
        return designation;
    }
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getAadhar() {
        return aadhar;
    }
    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }
}
