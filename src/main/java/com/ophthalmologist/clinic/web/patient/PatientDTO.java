package com.ophthalmologist.clinic.web.patient;

import com.ophthalmologist.clinic.models.Account;

public class PatientDTO extends Account {
    private String pesel;
    private String address;

    private String phone;
    private String sex;




    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

}
