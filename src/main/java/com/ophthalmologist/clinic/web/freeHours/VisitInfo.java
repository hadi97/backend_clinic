package com.ophthalmologist.clinic.web.freeHours;

import com.ophthalmologist.clinic.web.visits.Period;

import java.util.UUID;

public class VisitInfo {
    private String period;
    private Period p;
    private int doctorId;
    private String doctorName;
    private String date;
    private final UUID internalId;

    public VisitInfo() {
        internalId = UUID.randomUUID();
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public UUID getInternalId() {
        return internalId;
    }

    public Period getP() {
        return p;
    }

    public void setP(Period p) {
        this.p = p;
    }

    @Override
    public String toString() {
        return "VisitInfo{" +
                "period='" + period + '\'' +
                ", p=" + p +
                ", doctorId=" + doctorId +
                ", doctorName='" + doctorName + '\'' +
                ", date=" + date +
                ", internalId=" + internalId +
                '}';
    }
}
