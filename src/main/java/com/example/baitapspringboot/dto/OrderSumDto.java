package com.example.baitapspringboot.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class OrderSumDto {
    private Long cartId;
    private Long totalPrice;

    public OrderSumDto(Long cartId, Long totalPrice) {
        this.cartId = cartId;
        this.totalPrice = totalPrice;
    }

    public OrderSumDto() {
    }
}
