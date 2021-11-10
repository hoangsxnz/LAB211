/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmployeeManagement;

import Utility.GetValidInput;
import java.util.Date;

/**
 *
 * @author hoangson
 */
public class Employee implements Comparable<Employee> {

    private int ID;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String address;
    private Date DOB;
    private String sex;
    private double salary;
    private String agency;

    public Employee() {
    }

    public Employee(int ID, String firstName, String lastName, String phone, String email, String address, Date DOB, String sex, double salary, String agency) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.DOB = DOB;
        this.sex = sex;
        this.salary = salary;
        this.agency = agency;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    @Override
    public String toString() {
        return String.format("%-5s%-15s%-15s%-15s%-25s%-15s%-15s%-10s%-15s%-15s\n",
                ID, firstName, lastName, phone, email, address, GetValidInput.DatetoString(DOB, "dd/MM/yyyy"), sex, salary, agency);
    }

    @Override
    public int compareTo(Employee e) {
        return Double.compare(this.getSalary(), e.getSalary());
    }
}
