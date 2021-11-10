/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StudentManagement;

import Utility.GetValidInput;

/**
 *
 * @author hoangson
 */
public class Main {

    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        while (true) {
            // display menu
            displayMenu();
            // get user's choice
            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    // create student
                    manager.createStudent();
                    break;
                case 2:
                    // find students and sort (by name)
                    manager.findAndSortByName();
                    break;
                case 3:
                    // update or delete a student
                    manager.updateAndDelete();
                    break;
                case 4:
                    // report of students
                    manager.report();
                    break;
                case 5:
                    System.exit(0);
                    break;
            }
        }
    }

    public static void displayMenu() {
        System.out.println("========= Student Management ==========\n"
                + "1. Create student\n"
                + "2. Find and sort student\n"
                + "3. Update/Delete student\n"
                + "4. Report\n"
                + "5. Exit");
    }

    public static int getUserChoice() {
        int choice = GetValidInput.getInt("Enter choice: ", "Input can't be a string", 1, 5);
        return choice;
    }
}
