package com.ophthalmologist.clinic.web.visits;

import java.sql.Date;

public class AdminVisit {
    private int visitId;
    private String doctorName;
    private String patientName;
    private String canceled;
    private String treatmentName;
    private Date visitDate;


    public AdminVisit(int visitId, String doctorName, String patientName, String canceled,String treatmentName, Date visitDate) {
        this.visitId = visitId;
        this.doctorName = doctorName;
        this.patientName = patientName;
        this.treatmentName = treatmentName;
        this.visitDate = visitDate;
        this.canceled = canceled;
    }

    public int getVisitId() {
        return visitId;
    }

    public void setVisitId(int visitId) {
        this.visitId = visitId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getTreatmentName() {
        return treatmentName;
    }

    public void setTreatmentName(String treatmentName) {
        this.treatmentName = treatmentName;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public String isCancelled() {
        return canceled;
    }

    public void setCancelled(String cancelled) {
        this.canceled = cancelled;
    }

    @Override
    public String toString() {
        return "AdminVisit{" +
                "visitId=" + visitId +
                ", doctorName='" + doctorName + '\'' +
                ", patientName='" + patientName + '\'' +
                ", treatmentName='" + treatmentName + '\'' +
                ", visitDate=" + visitDate +
                ", isCanceled='" + canceled + '\'' +
                '}';
    }
}
