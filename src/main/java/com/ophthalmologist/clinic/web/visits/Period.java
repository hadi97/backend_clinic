package com.ophthalmologist.clinic.web.visits;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Period {


    H07_00("7:00"),
    H07_20("7:20"),
    H07_40("7:40"),
    H08_00("8:00"),
    H08_20("8:20"),
    H08_40("8:40"),
    H09_00("9:00"),
    H09_20("9:20"),
    H09_40("9:40"),
    H10_00("10:00"),
    H10_20("10:20"),
    H10_40("10:40"),
    H11_00("11:00"),
    H11_20("11:20"),
    H11_40("11:40"),
    H12_00("12:00"),
    H12_20("12:20"),
    H12_40("12:40"),
    H13_00("13:00"),
    H13_20("13:20"),
    H13_40("13:40"),
    H14_00("14:00"),
    H14_20("14:20"),
    H14_40("14:40"),
    H15_00("15:00"),
    H15_20("15:20"),
    H15_40("15:40"),
    H16_00("16:00"),
    H16_20("16:20"),
    H16_40("16:40"),
    H17_00("17:00"),
    H17_20("17:20"),
    H17_40("17:40"),
    H18_00("18:00"),
    H18_20("18:20"),
    H18_40("18:40");
    public String getHourName() {
        return hour;
    }

    Period(String s) {
        this.hour = s;
    }

    private String hour;

    public String getHour() {
        return hour;
    }


    //public static List<Period> toList() {
   //     return Arrays.asList(values());
  //  }

    public static List<Period> betweenHours(String startHour, String endHour) {
        Period startPeriod = valueOf("H" + startHour.replace(":", "_"));
        Period endPeriod = valueOf("H" + endHour.replace(":", "_"));
        return Arrays.stream(values()).
                filter(period -> period.ordinal() >= startPeriod.ordinal() && period.ordinal() <= endPeriod.ordinal()).
                collect(Collectors.toList());
    }
}
