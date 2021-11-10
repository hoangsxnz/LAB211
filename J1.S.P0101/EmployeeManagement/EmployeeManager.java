/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmployeeManagement;

import Utility.GetValidInput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 *
 * @author hoangson
 */
public class EmployeeManager {

    private ArrayList<Employee> empList;

    public EmployeeManager() {
        empList = new ArrayList<>();
    }

    public void addEmployee() {
        int ID;
        // user enter employee ID until not duplicated
        while (true) {
            ID = GetValidInput.getInt("Enter ID: ", "ID invalid", 1, Integer.MAX_VALUE);
            // check if ID does not exist in list
            if (getIndexOfID(ID) == -1) {
                break;
            } else {
                System.err.println("ID duplicated");
            }
        }
        String firstName = GetValidInput.getString("Enter first name: ", "First name invalid");
        String lastName = GetValidInput.getString("Enter last name: ", "Last name invalid");
        /*
        ^ matches the beginning of string
        \d matches any digit character (0-9)
        {9} matches 9 times \d as 9 digits
        $ matches the end of string
         */
        String phone = GetValidInput.getStringByRegex("Enter phone number: ", "Phone number invalid", "^0\\d{9}$");
        /*
        ^ matches the beginning of string
        [a-zA-Z] matches a character from a to z, not case sensitive
        \w* matches 0 or more of any word character
        @ matches a '@' character
        (\w+[.])+ matches 1 or more words with a dot at the end
        \w+ matches 1 or more of any word character
        $ matches the end of string
         */
        String email = GetValidInput.getStringByRegex("Enter email: ", "Email invalid", "^[a-zA-Z]\\w*@(\\w+[.])+\\w+$");
        String address = GetValidInput.getString("Enter address: ", "Address invalid");
        Date dob = GetValidInput.getDate("Enter DOB: ", "DOB invalid", "dd/MM/yyyy");
        /*
        ^ matches the beginning of string
        (male|female) matches exactly "male" or "female"
        $ matches the end of string
         */
        String sex = GetValidInput.getStringByRegex("Enter sex: ", "Sex invalid", "^(male|female)$");
        double salary = GetValidInput.getDouble("Enter salary: ", "Salary invalid", 0, Double.MAX_VALUE);
        String agency = GetValidInput.getString("Enter agency: ", "Agency invalid");
        empList.add(new Employee(ID, firstName, lastName, phone, email, address, dob, sex, salary, agency));
        System.out.println("Employee has been added");
    }

    public void updateEmployee() {
        if (empList.isEmpty()) {
            System.err.println("Empty list");
            return;
        }
        int ID = GetValidInput.getInt("Enter ID: ", "ID invalid", 1, Integer.MAX_VALUE);
        int indexOfID = getIndexOfID(ID);
        // check if ID does not exist in list
        if (indexOfID == -1) {
            System.err.println("Employee ID not found");
            return;
        }
        System.out.println("Enter new infomation of employee");
        Employee empUpdate = empList.get(getIndexOfID(ID));
        // user enter employee ID until not duplicated
        while (true) {
            ID = GetValidInput.getInt("Enter ID: ", "ID invalid", 1, Integer.MAX_VALUE);
            if (getIndexOfID(ID) == -1) {
                break;
            } else {
                System.err.println("ID duplicated");
            }
        }
        String firstName = GetValidInput.getString("Enter first name: ", "First name invalid");
        String lastName = GetValidInput.getString("Enter last name: ", "Last name invalid");
        /*
        ^ matches the beginning of string
        \d matches any digit character (0-9)
        {9} matches 9 times \d as 9 digits
        $ matches the end of string
         */
        String phone = GetValidInput.getStringByRegex("Enter phone number: ", "Phone number invalid", "^0\\d{9}$");
        /*
        ^ matches the beginning of string
        [a-zA-Z] matches a character from a to z, not case sensitive
        \w* matches 0 or more of any word character
        @ matches a '@' character
        (\w+[.])+ matches 1 or more words with a dot at the end
        \w+ matches 1 or more of any word character
        $ matches the end of string
         */
        String email = GetValidInput.getStringByRegex("Enter email: ", "Email invalid", "^[a-zA-Z]\\w*@(\\w+[.])+\\w+$");
        String address = GetValidInput.getString("Enter address: ", "Address invalid");
        Date dob = GetValidInput.getDate("Enter DOB: ", "DOB invalid", "dd/MM/yyyy");
        /*
        ^ matches the beginning of string
        (male|female) matches exactly "male" or "female"
        $ matches the end of string
         */
        String sex = GetValidInput.getStringByRegex("Enter sex: ", "Sex invalid", "^(male|female)$");
        double salary = GetValidInput.getDouble("Enter salary: ", "Salary invalid", 0, Double.MAX_VALUE);
        String agency = GetValidInput.getString("Enter agency: ", "Agency invalid");
        empUpdate.setID(ID);
        empUpdate.setFirstName(firstName);
        empUpdate.setLastName(lastName);
        empUpdate.setPhone(phone);
        empUpdate.setEmail(email);
        empUpdate.setAddress(address);
        empUpdate.setDOB(dob);
        empUpdate.setSex(sex);
        empUpdate.setSalary(salary);
        empUpdate.setAgency(agency);
        System.out.println("Employee has been updated");
    }

    public void removeEmployee() {
        if (empList.isEmpty()) {
            System.err.println("Empty list");
            return;
        }
        int ID = GetValidInput.getInt("Enter ID: ", "ID invalid", 1, Integer.MAX_VALUE);
        int indexOfID = getIndexOfID(ID);
        // check if ID does not exist in list
        if (indexOfID == -1) {
            System.err.println("Employee ID not found");
            return;
        }
        empList.remove(indexOfID);
        System.out.println("Employee has been removed");

    }

    public void searchEmployee() {
        String searchName = GetValidInput.getString("Enter search name: ", "Name invalid");
        int count = 0;
        // loop through all employees in list
        for (Employee employee : empList) {
            String fullName = employee.getFirstName() + " " + employee.getLastName();
            // check if employee's full name (first name + last name) contains the search string
            if (fullName.contains(searchName)) {
                if (count == 0) {
                    formatOutput();
                }
                System.out.println(employee);
                count++;
            }
        }
        // check if there is any employee found
        if (count == 0) {
            System.err.println("No employee found");
        }
    }

    public void sortBySalary() {
        if (empList.isEmpty()) {
            System.err.println("Empty list");
            return;
        }
        formatOutput();
        Collections.sort(empList);
    }

    public int getIndexOfID(int ID) {
        // loop through all indexes of employee in list
        for (int i = 0; i < empList.size(); ++i) {
            if (empList.get(i).getID() == ID) {
                return i;
            }
        }
        return -1;
    }

    public void display() {
        // loop through all employees in list
        for (Employee employee : empList) {
            System.out.println(employee);
        }
    }

    public void formatOutput() {
        System.out.format("%-5s%-15s%-15s%-15s%-25s%-15s%-15s%-10s%-15s%-15s\n",
                "ID", "First Name", "Last Name", "Phone", "Email", "Address", "DOB", "Sex", "Salary", "Agency");
    }
}
