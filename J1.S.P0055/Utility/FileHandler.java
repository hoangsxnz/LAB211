/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import DoctorManagement.Doctor;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;

/**
 *
 * @author hoangson
 */
public class FileHandler {

    public static void createFile() {
        try {
            File file = new File("C:\\Users\\ADMIN\\Desktop\\FPT\\Ki 3\\LAB211\\DoctorManagement\\src\\doctors.txt");
            // check if can not create file
            if (file.createNewFile()) {
                System.err.println("File has been created!");
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public static LinkedHashMap<String, Doctor> readFileToHashMap(String fileName) {
        LinkedHashMap<String, Doctor> doctorHash = new LinkedHashMap<>();
        BufferedReader reader = null;
        try {
            File file = new File(fileName);
            reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    String[] tokens = line.split(":");
                    String code = tokens[0].trim();
                    String name = tokens[1].trim();
                    String specialization = tokens[2].trim();
                    int availability = Integer.parseInt(tokens[3].trim());
                    doctorHash.put(code, new Doctor(code, name, specialization, availability));
                }
            }
        } catch (IOException ex) {
            doctorHash = null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ex) {
                    System.err.println(ex);
                }
            }
        }
        return doctorHash;
    }

    public static void writeHashMapToFile(LinkedHashMap<String, Doctor> doctorHash, String fileName) {
        BufferedWriter writer = null;
        try {
            File file = new File(fileName);
            writer = new BufferedWriter(new FileWriter(file));
            for (Doctor doc : doctorHash.values()) {
                writer.write(doc.getCode() + ":" + doc.getName() + ":" + doc.getSpecialization() + ":" + doc.getAvalability());
                writer.newLine();
            }
            writer.flush();
        } catch (IOException ex) {
            System.err.println(ex);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException ex) {
                    System.err.println(ex);
                }
            }
        }
    }
}
