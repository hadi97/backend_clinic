package com.ophthalmologist.clinic.web.freeHours;

public class AvailableDTO {
    private String hour;
    private String avalaible;

    public AvailableDTO(String hour, String avalaible) {
        this.hour = hour;
        this.avalaible = avalaible;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getAvalaible() {
        return avalaible;
    }

    public void setAvalaible(String avalaible) {
        this.avalaible = avalaible;
    }
}
