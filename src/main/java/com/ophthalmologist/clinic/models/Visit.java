package com.ophthalmologist.clinic.models;

import com.ophthalmologist.clinic.web.visits.Period;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "visits")
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int visitId;
    private Date date;
    private Period period;
    private int patientId;
    private boolean isConfirmed;
    private boolean isCanceled;
    private boolean isCompleted;
    private int duration;
    private String treatment;

    @Lob
    @Column
    private String notes;

    @Lob
    @Column
    private String advice;

    public Visit() {

    }

    public Visit( Date date, int patientId, boolean isCanceled, boolean isCompleted, int duration, String treatment, boolean isConfirmed, String advice, String notes) {
        this.date = date;
        this.patientId = patientId;
        this.isCanceled = isCanceled;
        this.isCompleted = isCompleted;
        this.duration = duration;
        this.treatment = treatment;
        this.isConfirmed = isConfirmed;
        this.notes = notes;
        this.advice = advice;

    }

    public Visit(int patientId, int doctorId, Date date, Period period, String treatment) {
        this.patientId = patientId;
        this.date = date;
        this.period = period;
        this.treatment = treatment;

    }

    public int getVisitId() {
        return visitId;
    }

    public void setVisitId(int visitId) {
        this.visitId = visitId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public void setCanceled(boolean canceled) {
        isCanceled = canceled;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    @Override
    public String toString() {
        return "Visit{" +
                "visitId=" + visitId +
                ", date=" + date +
                ", patientId=" + patientId +
                ", isCanceled=" + isCanceled +
                ", isCompleted=" + isCompleted +
                ", duration=" + duration +
                ", treatmentId=" + treatment +
                '}';
    }
}
