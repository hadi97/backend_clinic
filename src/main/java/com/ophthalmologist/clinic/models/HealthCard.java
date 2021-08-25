package com.ophthalmologist.clinic.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cards")
public class HealthCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int healthCardId;
    private int patientId;
    private String medicines;
    private String diseases;
    private String allergies;
    private String bleeding;
    private Date date;

    public HealthCard() {

    }

    public HealthCard(int patientId, String medicines, String diseases, String allergies,String bleeding,Date date) {
        this.patientId = patientId;
        this.medicines = medicines;
        this.diseases = diseases;
        this.allergies = allergies;
        this.bleeding = bleeding;
        this.date=date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getBleeding() {
        return bleeding;
    }

    public void setBleeding(String bleeding) {
        this.bleeding = bleeding;
    }


    public int getHealthCardId() {
        return healthCardId;
    }

    public void setHealthCardId(int healthCardId) {
        this.healthCardId = healthCardId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getMedicines() {
        return medicines;
    }

    public void setMedicines(String medicines) {
        this.medicines = medicines;
    }

    public String getDiseases() {
        return diseases;
    }

    public void setDiseases(String diseases) {
        this.diseases = diseases;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    @Override
    public String toString() {
        return "HealthCard{" +
                "healthCardId=" + healthCardId +
                ", patientId=" + patientId +
                ", medicines='" + medicines + '\'' +
                ", diseases='" + diseases + '\'' +
                ", allergies='" + allergies + '\'' +
                '}';
    }
}
