/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmployeeManagement;

import Utility.GetValidInput;

/**
 *
 * @author hoangson
 */
public class Main {

    public static void main(String[] args) {
        EmployeeManager manager = new EmployeeManager();
        while (true) {
            // display menu
            displayMenu();
            // get user's choice
            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    // add an employee
                    manager.addEmployee();
                    break;
                case 2:
                    // update an employee
                    manager.updateEmployee();
                    break;
                case 3:
                    // remove an employee
                    manager.removeEmployee();
                    break;
                case 4:
                    // search an employee
                    manager.searchEmployee();
                    break;
                case 5:
                    // sort all employees by salary
                    manager.sortBySalary();
                    // display all employees
                    manager.display();
                    break;
                case 6:
                    System.exit(0);
                    break;
            }
        }
    }

    public static void displayMenu() {
        System.out.println("========= Employee Management ==========\n"
                + "1. Add employees\n"
                + "2. Update employees\n"
                + "3. Remove employees\n"
                + "4. Search employees\n"
                + "5. Sort employees by salary\n"
                + "6. Exit");
    }

    public static int getUserChoice() {
        int choice = GetValidInput.getInt("Enter choice: ", "Input can't be a string", 1, 6);
        return choice;
    }
}
