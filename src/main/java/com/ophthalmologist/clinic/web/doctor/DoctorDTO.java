package com.ophthalmologist.clinic.web.doctor;

import com.ophthalmologist.clinic.models.Account;

public class DoctorDTO extends Account {

    private String phone;
    private int doctorId;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

}


