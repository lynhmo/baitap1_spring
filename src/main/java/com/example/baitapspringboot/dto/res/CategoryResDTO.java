package com.example.baitapspringboot.dto.res;

import com.example.baitapspringboot.model.Category;
import lombok.Data;

import java.util.List;

@Data
public class CategoryResDTO {
    private List<?> data;
    private Integer statusCode;
    private String message;

    public CategoryResDTO(List<?> data) {
        this.statusCode = 200;
        this.message = "SUCCESS";
        this.data = data;
    }
}
