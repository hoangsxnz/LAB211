/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StudentManagement;

/**
 *
 * @author hoangson
 */
public class Student implements Comparable<Student> {
    private int ID;
    private String name;
    private int semester;
    private String courseName;

    public Student(int ID, String name, int semester, String courseName) {
        this.ID = ID;
        this.name = name;
        this.semester = semester;
        this.courseName = courseName;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public int compareTo(Student t) {
        return this.getName().compareTo(t.getName());
    }
}
