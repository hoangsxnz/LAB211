/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DoctorManagement;

/**
 *
 * @author hoangson
 */
public class Doctor {

    private String code;
    private String name;
    private String specialization;
    private int avalability;

    public Doctor() {
    }

    public Doctor(String code, String name, String specialization, int avalability) {
        this.code = code;
        this.name = name;
        this.specialization = specialization;
        this.avalability = avalability;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getAvalability() {
        return avalability;
    }

    public void setAvalability(int avalability) {
        this.avalability = avalability;
    }

    @Override
    public String toString() {
        return "Doctor{" + "code=" + code + ", name=" + name + ", specialization=" + specialization + ", avalability=" + avalability + '}';
    }
}
