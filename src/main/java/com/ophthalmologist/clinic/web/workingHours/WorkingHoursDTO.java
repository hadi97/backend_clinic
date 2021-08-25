package com.ophthalmologist.clinic.web.workingHours;

import com.ophthalmologist.clinic.models.WorkingHours;

public class WorkingHoursDTO  {

    private String dayName;
    private String startHour;
    private String endHour;

    public WorkingHoursDTO(String dayName, String startHour, String endHour) {
        this.dayName = dayName;
        this.startHour = startHour;
        this.endHour = endHour;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public String getStartHour() {
        return startHour;
    }

    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }

    public String getEndHour() {
        return endHour;
    }

    public void setEndHour(String endHour) {
        this.endHour = endHour;
    }


}
