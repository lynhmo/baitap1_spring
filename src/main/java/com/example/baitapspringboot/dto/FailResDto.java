package com.example.baitapspringboot.dto;

import lombok.Data;

@Data
public class FailResDto extends CommonDTO {


    public FailResDto(Integer statusCode, String status, String message) {
        super();
    }

    public FailResDto() {
    }
}
