package com.ophthalmologist.clinic.models;

import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int patientId;
    private String address;
    private Date birthDate;
    private String phone;
    private String pesel;
    private int accountId;
    private String isMale;

    public Patient() {

    }

    public Patient(String address, String phone, String pesel, int accountId, String isMale) {
        this.address = address;
        this.phone = phone;
        this.pesel = pesel;
        this.isMale = isMale;
        this.accountId = accountId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public int getUserId() {
        return accountId;
    }

    public void setUserId(int userId) {
        this.accountId = userId;
    }

    public String isMale() {
        return isMale;
    }

    public void setMale(String male) {
        isMale = male;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientId=" + patientId +
                ", address='" + address + '\'' +
                ", birthDate=" + birthDate +
                ", phone=" + phone +
                ", pesel=" + pesel +
                ", userId=" + accountId +
                '}';
    }
}
