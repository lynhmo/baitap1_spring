package com.example.baitapspringboot.dto;

import lombok.Data;

@Data
public class OrderDetailProductDto {
    private Long id_prod;
    private Integer quantity;

    public OrderDetailProductDto(Long id_prod, Integer quantity) {
        this.id_prod = id_prod;
        this.quantity = quantity;
    }

    public OrderDetailProductDto() {
    }
}

