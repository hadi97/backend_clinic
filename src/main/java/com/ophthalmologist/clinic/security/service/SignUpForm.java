package com.ophthalmologist.clinic.security.service;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

public class SignUpForm {
    @NotBlank String username;
    @NotBlank
    @Size(min = 8, max = 30)
    private String password;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private String pesel;

    private String address;

    private String sex;
    private String phone;


    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}