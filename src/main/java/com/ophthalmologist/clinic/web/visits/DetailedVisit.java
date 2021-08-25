package com.ophthalmologist.clinic.web.visits;


import java.sql.Date;

public class DetailedVisit {
    private int visitId;
    private String doctorName;
    private String patientName;
    private String treatmentName;
    private Date visitDate;
    private String advice;
    private String notes;
    private String confirmed;
    DetailedVisit(int visitId, String doctorName, String patientName, String treatmentName, Date visitDate, String advice,String notes,String confirmed) {
        this.visitId = visitId;
        this.doctorName = doctorName;
        this.patientName = patientName;
        this.treatmentName = treatmentName;
        this.visitDate = visitDate;
        this.confirmed=confirmed;
        this.advice=advice;
        this.notes=notes;
    }

    public int getVisitId() {
        return visitId;
    }

    public void setVisitId(int visitId) {
        this.visitId = visitId;
    }

    public String getTreatmentName() {
        return treatmentName;
    }

    public void setTreatmentName(String treatmentName) {
        this.treatmentName = treatmentName;
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

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }


    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }
}
