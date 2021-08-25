package com.ophthalmologist.clinic.web.freeHours;

import java.util.UUID;

public class FreeDTO {
    private String date;
    private Integer id;
    private final UUID internalId;
    public FreeDTO(String date) {
        internalId = UUID.randomUUID();
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UUID getInternalId() {
        return internalId;
    }
}
