/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DoctorManagement;

import Utility.GetValidInput;

/**
 *
 * @author hoangson
 */
public class Main {

    public static void main(String[] args) {
        DoctorHash manager = new DoctorHash();
        while (true) {
            // display GUI
            displayMenu();
            // get choice from user
            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    // create a new doctor
                    Doctor newDoctor = manager.createDoctor("--------- Add Doctor ----------");
                    // add created doctor to database
                    manager.addDoctorToDB(newDoctor);
                    break;
                case 2:
                    // search a doctor based on code or a part of code
                    Doctor doctorUpdate = manager.searchByCode("--------- Update Doctor -------");
                    // update doctor in database
                    manager.updateDoctorInDB(doctorUpdate);
                    break;
                case 3:
                    // search a doctor based on code or a part of code
                    Doctor doctorDelete = manager.searchByCode("--------- Delete Doctor -------");
                    // delete doctor (if found in database)
                    manager.deleteDoctorInDB(doctorDelete);
                    break;
                case 4:
                    // get a string from user to search
                    String text = manager.getSearchText("--------- Search Doctor -------");
                    // search doctor in database
                    manager.searchDoctorInDB(text);
                    break;
                case 5:
                    System.exit(0);
                    break;
            }
        }
    }

    public static void displayMenu() {
        System.out.println("========= Doctor Management ==========\n"
                + "●    Add Doctor.\n"
                + "●    Update Doctor.\n"
                + "●    Delete Doctor.\n"
                + "●    Search Doctor.\n"
                + "●    Exit.");
    }

    public static int getUserChoice() {
        int choice = GetValidInput.getInt("Enter your choice: ", "Input can't be a string!", 1, 5);
        return choice;
    }
}
