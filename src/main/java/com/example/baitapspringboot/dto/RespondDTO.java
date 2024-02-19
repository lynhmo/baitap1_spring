package com.example.baitapspringboot.dto;

import lombok.Data;

@Data
public class RespondDTO {
    private String message;
    private String status;
    private Integer statusCode;

    public RespondDTO(String message, String status, Integer statusCode) {
        this.message = message;
        this.status = status;
        this.statusCode = statusCode;
    }
}
