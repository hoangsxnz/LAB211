/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChangeBase;

import Utility.GetValidInput;

/**
 *
 * @author hoangson
 */
public class Main {

    public static void main(String[] args) {
        Converter Converter = new Converter();
        while (true) {
            // display menu
            displayMenu();
            // get user's choice
            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    // require user to choose the base number input
                    int baseIn = getBaseIn();
                    // require user to choose the base number output
                    int baseOut = getBaseOut();
                    // require user to enter the input value
                    String inputValue = getInputValue(baseIn);
                    // convert base of input number to output base
                    String outputValue = Converter.convert(inputValue, baseIn, baseOut);
                    // display result
                    displayResult(inputValue, baseIn, outputValue, baseOut);
                    break;
                case 2:
                    System.exit(0);
                    break;
            }
        }
    }

    public static int getBaseIn() {
        int baseIn = GetValidInput.getBase("Choose the base number input: ", "Base invalid!");
        return baseIn;
    }

    public static int getBaseOut() {
        int baseOut = GetValidInput.getBase("Choose the base number output: ", "Base invalid!");
        return baseOut;
    }

    public static String getInputValue(int baseIn) {
        String inputValue = GetValidInput.getValueWithBase("Enter the input value: ", "Value invalid!", baseIn);
        return inputValue;
    }

    private static void displayMenu() {
        System.out.println("1. Change base number\n" + "2. Exit");
    }

    private static int getUserChoice() {
        int choice = GetValidInput.getInt("Your choice: ", "Input can't be a string", 1, 2);
        return choice;
    }

    private static void displayResult(String inputValue, int baseIn, String outputValue, int baseOut) {
        System.out.println("Result after convert:");
        System.out.println(inputValue + "(" + baseIn + ") = " + outputValue + "(" + baseOut + ")");
    }
}
