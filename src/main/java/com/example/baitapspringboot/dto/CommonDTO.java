package com.example.baitapspringboot.dto;

import lombok.Data;

@Data
public class CommonDTO {
    private Integer statusCode;
    private String message;
    private Object data;
    private String status;

    public CommonDTO() {
    }

    public CommonDTO(Integer statusCode, String message, Object data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public CommonDTO(Object data) {
        this.data = data;
        this.statusCode = 200;
        this.message = "SUCCESS";
    }

    public CommonDTO(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public CommonDTO(Integer statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public CommonDTO(Integer statusCode, String status, String message) {
        this.statusCode = statusCode;
        this.status = status;
        this.message = message;
    }

    public CommonDTO(Integer statusCode, String status, String message, Object data) {
        this.statusCode = statusCode;
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
