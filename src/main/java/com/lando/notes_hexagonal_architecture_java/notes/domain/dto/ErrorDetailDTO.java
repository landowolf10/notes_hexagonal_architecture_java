package com.lando.notes_hexagonal_architecture_java.notes.domain.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class ErrorDetailDTO {
    private long timeStamp;
    private int status;
    private String title;
    private String detail;

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setErrors(Map<String, List<ValidationErrorDTO>> errors) {
        this.errors = errors;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public int getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }

    public Map<String, List<ValidationErrorDTO>> getErrors() {
        return errors;
    }

    private Map<String, List<ValidationErrorDTO>> errors = new HashMap<>();
}