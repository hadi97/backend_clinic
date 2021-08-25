package com.ophthalmologist.clinic.models;

import javax.persistence.*;

@Entity
@Table(name = "treatments")
public class Treatment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int treatmentId;
    private String treatmentName;
    private String description;
    private int duration;

    public Treatment (){
    }

    public Treatment(String description, int duration) {
        this.description = description;
        this.duration = duration;
    }

    public int getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(int treatmentId) {
        this.treatmentId = treatmentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getTreatmentName() {
        return treatmentName;
    }

    public void setTreatmentName(String treatmentName) {
        this.treatmentName = treatmentName;
    }

    @Override
    public String toString() {
        return "Treatment{" +
                "treatmentId=" + treatmentId +
                ", description='" + description + '\'' +
                ", duration=" + duration +
                '}';
    }
}
