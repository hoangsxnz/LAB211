/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CountryManagement;

import Utility.GetValidInput;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author hoangson
 */
public class ManageEastAsiaCountries {

    private ArrayList<EastAsiaCountries> listCountry;

    public ManageEastAsiaCountries() {
        listCountry = new ArrayList<>();
    }

    public void addCountryInformation() {
        if(listCountry.size() == 3) {
            System.err.println("Enough information for 3 countries");
            return;
        }
        int count = 0;
        // loop until user enter enough information for 3 countries
        while (count < 3) {
            System.out.println("Enter information for country " + (count+1) + ":");
            String code;
            // loop until user enter a non-duplicated code
            while (true) {
                /*
                ^ matches the beginning of string
                [A-Z]+ matches 1 or more character in range A-Z, case sensitive
                $ matches the end of string
                */
                code = GetValidInput.getStringByRegex("Enter code of country:\n", "Code invalid", "^[A-Z]+$");
                if (isExist(code)) {
                    System.err.println("Duplicated, please enter another code");
                } else {
                    break;
                }
            }
            /*
            ^ matches the beginning of string
            [a-zA-Z ]+ matches 1 or more character in range a-z (not case sensitive) and 1 or more space character
            $ matches the end of string
            */
            String name = GetValidInput.getStringByRegex("Enter name of country:\n", "Name invalid", "^[a-zA-Z ]+$");
            float totalArea = GetValidInput.getFloat("Enter total Area:\n", "Total area invalid", Float.MIN_VALUE, Float.MAX_VALUE);
            /*
            ^ matches the beginning of string
            [a-zA-Z ]+ matches 1 or more character in range a-z (not case sensitive) and 1 or more space character
            $ matches the end of string
            */
            String terrain = GetValidInput.getStringByRegex("Enter terrain of country:\n", "Terrain invalid", "^[a-zA-Z ]+$");
            listCountry.add(new EastAsiaCountries(code, name, totalArea, terrain));
            System.out.println("Country has been added");
            count++;
        }
        System.out.println("3 countries has been added");
    }

    public void displayInformation() {
        // check if the list of countries is empty
        if (emptyList()) {
            System.err.println("Empty list");
            return;
        }
        formatOutput();
        // loop through all countries in list of countries
        for (EastAsiaCountries country : listCountry) {
            country.display();
        }
    }

    public void searchCountryByName() {
        // check if the list of countries is empty
        if (emptyList()) {
            System.err.println("Empty list");
            return;
        }
        String searchName = GetValidInput.getString("Enter the name you want to search for:\n", "Name invalid");
        int numOfCountryFound = 0;
        // loop through all countries in list of countries
        for (EastAsiaCountries country : listCountry) {
            if (country.getCountryName().contains(searchName)) {
                if (numOfCountryFound == 0) {
                    formatOutput();
                }
                country.display();
                numOfCountryFound++;
            }
        }
        // check if there isn't any country found
        if (numOfCountryFound == 0) {
            System.err.println("No country found");
        }
    }

    public void displayByCountryNameAscending() {
        Collections.sort(listCountry);
        displayInformation();
    }

    private boolean isExist(String code) {
        // loop through all countries in list of countries
        for (EastAsiaCountries country : listCountry) {
            // compare input code to the code of each country
            if (country.getCountryCode().equalsIgnoreCase(code)) {
                return true;
            }
        }
        return false;
    }

    public boolean emptyList() {
        return listCountry.isEmpty();
    }

    public void formatOutput() {
        System.out.format("%-20s%-20s%-20s%-20s\n", "ID", "Name", "Total Area", "Terrain");
    }
}
