package me.restapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public class Task {

    private UUID uuid;
    private String status;
    private String timestamp;

    @JsonIgnore
    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}
