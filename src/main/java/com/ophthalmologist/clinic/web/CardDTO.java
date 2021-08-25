package com.ophthalmologist.clinic.web;


public class CardDTO {
    private int healthCardId;
    private int patientId;
    private String medicines;
    private String diseases;
    private String allergies;
    private String bleeding;
    private String date;

    public CardDTO(int healthCardId, int patientId, String medicines, String diseases, String allergies, String bleeding, String date) {
        this.healthCardId = healthCardId;
        this.patientId = patientId;
        this.medicines = medicines;
        this.diseases = diseases;
        this.allergies = allergies;
        this.bleeding = bleeding;
        this.date = date;
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

    public String getBleeding() {
        return bleeding;
    }

    public void setBleeding(String bleeding) {
        this.bleeding = bleeding;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
