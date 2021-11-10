/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DoctorManagement;

import Utility.FileHandler;
import java.util.LinkedHashMap;
import Utility.GetValidInput;

/**
 *
 * @author hoangson
 */
public class DoctorHash {

    private final String fileDirectory = "C:\\Users\\ADMIN\\Desktop\\FPT\\Ki 3\\LAB211\\DoctorManagement\\src\\doctors.txt";

    public LinkedHashMap<String, Doctor> loadDatabase() {
        LinkedHashMap<String, Doctor> doctorHash = FileHandler.readFileToHashMap(fileDirectory);
        return doctorHash;
    }

    // create a new doctor
    public Doctor createDoctor(String msg) {
        System.out.println(msg);
        LinkedHashMap<String, Doctor> doctorHash = loadDatabase();
        if (doctorHash != null) {
            display();
        }
        // new data for doctor
        String code = GetValidInput.getString("Enter code: ", "Code invalid!");
        String name = GetValidInput.getString("Enter name: ", "Name invalid!");
        String specialization = GetValidInput.getString("Enter specialization: ", "Specialization invalid!");
        int availability = GetValidInput.getInt("Enter availability: ", "Availability invalid!", 0, Integer.MAX_VALUE);
        if (code == null || name == null || specialization == null) {
            return null;
        }
        return new Doctor(code, name, specialization, availability);
    }

    // search doctor by code or a part of code
    public Doctor searchByCode(String msg) {
        System.out.println(msg);
        LinkedHashMap<String, Doctor> doctorHash = loadDatabase();
        // check if file does not exist
        if (doctorHash == null) {
            return null;
        }
        // check if database is empty or not
        if (doctorHash.isEmpty()) {
            return null;
        }
        display();
        String searchCode = GetValidInput.getStringIgnoreNull("Enter code: ", "Code invalid!");
        // check if doctor with search code is in database
        if (doctorHash.containsKey(searchCode)) {
            return doctorHash.get(searchCode);
        } else {
            return new Doctor();
        }
    }

    // get a text to search from 3 attributes: code, name and specialization
    public String getSearchText(String msg) {
        System.out.println(msg);
        String text = GetValidInput.getStringIgnoreNull("Enter text: ", "Invalid input");
        return text;
    }

    // add a doctor to database
    public void addDoctorToDB(Doctor doctor) {
        LinkedHashMap<String, Doctor> doctorHash = loadDatabase();
        // check if file does not exist
        if (doctorHash == null) {
            System.err.println("Database does not exist");
            FileHandler.createFile();
            doctorHash = loadDatabase();
        }
        // check if parameter is not null 
        if (doctor != null) {
            if (!doctorHash.containsKey(doctor.getCode())) {
                doctorHash.put(doctor.getCode(), doctor);
                FileHandler.writeHashMapToFile(doctorHash, fileDirectory);
                System.out.println("Doctor has been added!");
                display();
            } else {
                System.err.println("Doctor code " + doctor.getCode() + " is duplicated");
            }
        } else {
            System.err.println("Data does not exist");
        }
    }

    // update a doctor based on code
    public void updateDoctorInDB(Doctor doctor) {
        LinkedHashMap<String, Doctor> doctorHash = loadDatabase();
        // check if file does not exist
        if (doctorHash == null) {
            System.err.println("Database does not exist");
            return;
        }
        // check if parameter is null doctor
        if (doctor == null) {
            System.err.println("Data doesn't exist");
            return;
        }
        // check if code of doctor is in database
        if (!doctorHash.containsKey(doctor.getCode())) {
            System.err.println("Doctor code doesn't exist");
            return;
        } else {
            doctor = doctorHash.get(doctor.getCode());
        }
        // new data for doctor
        String code;
        while (true) {
            code = GetValidInput.getStringIgnoreNull("Enter new code: ", "Code invalid!");
            // check if database already has code of the doctor to be updated
            if (doctorHash.containsKey(code)) {
                System.err.println("Doctor code " + code + " is duplicated");
            } else {
                break;
            }
        }
        String name = GetValidInput.getStringIgnoreNull("Enter name: ", "Name invalid!");
        String specialization = GetValidInput.getStringIgnoreNull("Enter specialization: ", "Specialization invalid!");
        int availability = GetValidInput.getInt("Enter availability: ", "Availability invalid!", 0, Integer.MAX_VALUE);
        doctor.setCode(code);
        doctor.setName(name);
        doctor.setSpecialization(specialization);
        doctor.setAvalability(availability);
        FileHandler.writeHashMapToFile(doctorHash, fileDirectory);
        System.out.println("Doctor has been updated!");
        display();
    }

    // delete a doctor based on code
    public void deleteDoctorInDB(Doctor doctor) {
        LinkedHashMap<String, Doctor> doctorHash = loadDatabase();
        // check if file does not exist
        if (doctorHash == null) {
            System.err.println("Database does not exist");
            return;
        }
        // check if parameter is null doctor
        if (doctor == null) {
            System.err.println("Data doesn't exist");
            return;
        }
        // check if database has code of doctor
        if (!doctorHash.containsKey(doctor.getCode())) {
            System.err.println("Doctor code doesn't exist");
            return;
        }
        doctorHash.remove(doctor.getCode());
        FileHandler.writeHashMapToFile(doctorHash, fileDirectory);
        System.out.println("Doctor has been removed");
        display();
    }

    // search a doctor based on input string
    public void searchDoctorInDB(String input) {
        LinkedHashMap<String, Doctor> doctorHash = loadDatabase();
        // check if file does not exist
        if (doctorHash == null) {
            System.err.println("Database does not exist");
            return;
        }
        // check if database is empty or not
        if (doctorHash.isEmpty()) {
            System.err.println("Data doesn't exist");
            return;
        }
        System.out.println("--------- Result --------------");
        formatOutput("Code", "Name", "Specialization", "Availability");
        int numOfDoctorFound = 0;
        // loop through all values in hashmap
        for (Doctor doctor : doctorHash.values()) {
            if (doctor.getCode().contains(input) || doctor.getName().contains(input) || doctor.getSpecialization().contains(input)) {
                formatOutput(doctor.getCode(), doctor.getName(), doctor.getSpecialization(), "" + doctor.getAvalability());
                numOfDoctorFound++;
            }
        }
        // check if there is any doctor found
        if (numOfDoctorFound == 0) {
            System.err.println("No doctor found!");
        }
    }

    public void display() {
        LinkedHashMap<String, Doctor> doctorHash = loadDatabase();
        formatOutput("Code", "Name", "Specialization", "Availability");
        // loop to access all values in hashmap
        for (Doctor doctor : doctorHash.values()) {
            formatOutput(doctor.getCode(), doctor.getName(), doctor.getSpecialization(), "" + doctor.getAvalability());
        }
    }

    public void formatOutput(String s1, String s2, String s3, String s4) {
        System.out.format("%-10s%-10s%-20s%-20s", s1, s2, s3, s4);
        System.out.println();
    }
}
