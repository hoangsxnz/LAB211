/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StudentManagement;

import Utility.GetValidInput;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author hoangson
 */
public class StudentManager {

    private ArrayList<Student> studentList;

    public StudentManager() {
        studentList = new ArrayList<>();
    }

    public void createStudent() {
        // check if student list contains 3 or more records
        if (studentList.size() >= 3) {
            /* 
            ^ matches the beginning of string
            [YNyn] matches any character in the set
            $ matches the end of string
             */
            String choice = GetValidInput.getStringByRegex("Do you want to continue (Y/N)?\n", "Input invalid", "^[YNyn]$");
            if (choice.equalsIgnoreCase("N")) {
                return;
            }
        }
        // loop until student has been added to list
        while (true) {
            int ID;
            String name;
            // loop until user enter name match with ID if ID exist
            while (true) {
                ID = GetValidInput.getInt("Enter ID: ", "ID invalid", 1, Integer.MAX_VALUE);
                name = GetValidInput.getString("Enter name: ", "Name invalid");
                // check if input ID matches with input name
                if (checkIDAndNameNotMatch(ID, name)) {
                    System.err.println("This ID belongs to another student. Enter again");
                } else {
                    break;
                }
            }
            int semester = GetValidInput.getInt("Enter semester: ", "Semester invalid", 1, Integer.MAX_VALUE);
            String courseName = GetValidInput.getCourse();
            // check if there is another record with the same ID, semester, course name
            if (checkDuplicateStudent(ID, semester, courseName)) {
                System.err.println("Duplicated student info. Enter again");
            } else {
                studentList.add(new Student(ID, name, semester, courseName));
                System.out.println("Student has been created");
                break;
            }
        }
    }

    public void findAndSortByName() {
        if (studentList.isEmpty()) {
            System.err.println("Empty list");
            return;
        }
        String searchName = GetValidInput.getString("Enter search name: ", "Search name invalid");
        // declare a list of student with the same name
        ArrayList<Student> foundList = new ArrayList<>();
        // loop through every students in student list
        for (Student student : studentList) {
            // check if student's name equals or contains a part of search name
            if (student.getName().contains(searchName)) {
                foundList.add(student);
            }
        }
        // check if there is any student found
        if (foundList.isEmpty()) {
            System.err.println("No student found");
            return;
        }
        Collections.sort(foundList);
        formatOutput("Student name", "Semester", "Course name");
        // loop through every students in found list
        for (Student student : foundList) {
            formatOutput(student.getName(), student.getSemester() + "", student.getCourseName());
        }
    }

    public void updateAndDelete() {
        if (studentList.isEmpty()) {
            System.err.println("Empty list");
            return;
        }
        int ID = GetValidInput.getInt("Enter search ID: ", "Search ID invalid", 1, Integer.MAX_VALUE);
        // declare a list of student with the same ID
        ArrayList<Student> foundList = new ArrayList<>();
        // loop through all index of student in student list
        for (int i = 0; i < studentList.size(); ++i) {
            // check if input ID equals to current student ID
            if (ID == studentList.get(i).getID()) {
                foundList.add(studentList.get(i));
            }
        }
        // check if there is any student found
        if (foundList.isEmpty()) {
            System.err.println("Student ID not found");
            return;
        }
        System.out.println("List student found:");
        formatOutput("Number", "ID", "Name", "Semester", "Course name");
        int number = 1;
        // loop through all student in found list to print them out
        for (int i = 0; i < foundList.size(); ++i) {
            formatOutput(number + "", foundList.get(i).getID() + "", foundList.get(i).getName(), foundList.get(i).getSemester() + "", foundList.get(i).getCourseName());
            number++;
        }
        int studentNo = GetValidInput.getInt("Enter student number to update/delete: ", "Student number invalid", 1, number);
        Student studentModify = foundList.get(studentNo - 1);
        /*
        ^ matches the beginning of string
        [UDud] matches any character in the set
        $ matches the end of string
         */
        String choice = GetValidInput.getStringByRegex("Do you want to update (U) or delete (D) student? ", "Input invalid", "^[UDud]$");
        // check if user in put 'D' or 'd'
        if (choice.equalsIgnoreCase("D")) {
            studentList.remove(studentModify);
            System.out.println("Student has been removed");
        } else {
            // loop until studentModify has been updated
            while (true) {
                int newID = GetValidInput.getInt("Enter new ID: ", "ID invalid", 1, Integer.MAX_VALUE);
                String newName = GetValidInput.getString("Enter new name: ", "Name invalid");
                int newSemester = GetValidInput.getInt("Enter new semester: ", "Semester invalid", 1, Integer.MAX_VALUE);
                String newCourseName = GetValidInput.getCourse();
                // check if there is another record with the same ID, semester, course name
                if (checkDuplicateStudent(newID, newSemester, newCourseName)) {
                    System.err.println("Duplicated student info. Enter again");
                } else {
                    // loop through all students in list, change all student with new ID to new Name
                    for (int i = 0; i < studentList.size(); ++i) {
                        if (newID == studentList.get(i).getID()) {
                            studentList.get(i).setName(newName);
                        }
                    }
                    studentModify.setID(newID);
                    studentModify.setName(newName);
                    studentModify.setSemester(newSemester);
                    studentModify.setCourseName(newCourseName);
                    System.out.println("Student has been updated");
                    break;
                }
            }
        }
    }

    public void report() {
        if (studentList.isEmpty()) {
            System.err.println("Empty list");
            return;
        }
        // declare a list to hold all reports
        ArrayList<Report> report = new ArrayList<>();
        // loop to get all students in student list
        for (int i = 0; i < studentList.size(); ++i) {
            int totalCourse = 0;
            int ID1 = studentList.get(i).getID();
            String courseName1 = studentList.get(i).getCourseName();
            // loop to get all students in student list
            for (int j = 0; j < studentList.size(); ++j) {
                int ID2 = studentList.get(j).getID();
                String courseName2 = studentList.get(j).getCourseName();
                // check if 2 students have the same ID
                if (ID1 == ID2 && courseName1.equalsIgnoreCase(courseName2)) {
                    totalCourse++;
                }
            }
            // check if there is any duplicated report
            if (!checkReportExist(report, ID1, courseName1)) {
                report.add(new Report(ID1, studentList.get(i).getName(), courseName1, totalCourse));
            }
        }
        System.out.println("The report as below:");
        // loop through every report in list of reports
        for (Report currentReport : report) {
            System.out.format("%-15s|%-8s|%-5s", currentReport.getName(), currentReport.getCourseName(), currentReport.getTotalCourse());
            System.out.println();
        }
    }

    public boolean checkStudentIDExist(int ID) {
        // loop through all indexes of students in list
        for (int i = 0; i < studentList.size(); ++i) {
            // check if current student ID equals to input ID
            if (studentList.get(i).getID() == ID) {
                return true;
            }
        }
        return false; // not found
    }

    public boolean checkIDAndNameNotMatch(int ID, String name) {
        // loop through all students in student list
        for (Student student : studentList) {
            // check if current student ID equals input ID but current student name is not input name
            if (ID == student.getID() && !name.equalsIgnoreCase(student.getName())) {
                return true;
            }
        }
        return false;
    }

    public boolean checkDuplicateStudent(int ID, int semester, String courseName) {
        // loop through all students in student list
        for (Student student : studentList) {
            // check if a record with current student ID, semester and course name exists
            if (ID == student.getID() && semester == student.getSemester() && courseName.equalsIgnoreCase(student.getCourseName())) {
                return true;
            }
        }
        return false;
    }

    public void formatOutput(String s1, String s2, String s3) {
        System.out.format("%-15s%-10s%-7s", s1, s2, s3);
        System.out.println();
    }

    public void formatOutput(String s1, String s2, String s3, String s4, String s5) {
        System.out.format("%-8s%-8s%-15s%-10s%-7s", s1, s2, s3, s4, s5);
        System.out.println();
    }

    public boolean checkReportExist(ArrayList<Report> report, int ID, String courseName) {
        // loop through every report in list of reports
        for (Report currentReport : report) {
            if (currentReport.getID() == ID && currentReport.getCourseName().equalsIgnoreCase(courseName)) {
                return true;
            }
        }
        return false;
    }
}
