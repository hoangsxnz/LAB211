/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TPBank;

import Utility.GetValidInput;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author hoangson
 */
public class Main {

    public static void main(String[] args) {
        Ebank ebank = new Ebank();
        ebank.mockAccount();
        while (true) {
            // display menu
            displayMenu();
            // get user's choice
            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    // login with Vietnamese
                    ebank.login(new Locale("Vi"));
                    break;
                case 2:
                    // login with English
                    ebank.login(new Locale("En"));
                    break;
                case 3:
                    System.exit(0);
                    break;
            }
        }
    }

    public static void displayMenu() {
        System.out.println("-------Login Program-------\n"
                + "1. Vietnamese\n"
                + "2. English\n"
                + "3. Exit");
    }

    public static int getUserChoice() {
        int choice = GetValidInput.getInt("Enter choice: ", "Input can't be a string", 1, 3);
        return choice;
    }
}
