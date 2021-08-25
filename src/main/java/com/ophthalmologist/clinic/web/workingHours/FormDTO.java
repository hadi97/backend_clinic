package com.ophthalmologist.clinic.web.workingHours;

public class FormDTO {
    private String date;
    private String dateStart;
    private String dateStop;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    String getDateStop() {
        return dateStop;
    }

    public void setDateStop(String dateStop) {
        this.dateStop = dateStop;
    }

}
