/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CountryManagement;

import Utility.GetValidInput;

/**
 *
 * @author hoangson
 */
public class Main {

    public static void main(String[] args) {
        ManageEastAsiaCountries manager = new ManageEastAsiaCountries();
        while (true) {
            // display menu
            displayMenu();
            // get user's choice
            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    manager.addCountryInformation();
                    break;
                case 2:
                    manager.displayInformation();
                    break;
                case 3:
                    manager.searchCountryByName();
                    break;
                case 4:
                    manager.displayByCountryNameAscending();
                    break;
                case 5:
                    System.exit(0);
                    break;
            }
        }
    }

    private static void displayMenu() {
        System.out.println("                                MENU\n"
                + "===========================================================================\n"
                + "1. Input the information of 3 countries in East Asia\n"
                + "2. Display the information of country you've just input\n"
                + "3. Search the information of country by user-entered name\n"
                + "4. Display the information of countries sorted name in ascending order\n"
                + "5. Exit\n"
                + "===========================================================================");
    }

    private static int getUserChoice() {
        int choice = GetValidInput.getInt("Enter your choice: ", "Input can't be a string!", 1, 5);
        return choice;
    }
}
