package com.ophthalmologist.clinic.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "working_hours")
public class WorkingHours {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private Timestamp startDate;
    private Timestamp endDate;

    public WorkingHours(int doctorId, Timestamp startDate, Timestamp endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public WorkingHours(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }




}
