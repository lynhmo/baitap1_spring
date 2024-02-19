package com.example.baitapspringboot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "hoa_don_chi_tiet")
@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "so_luong")
    private Integer quantity;
    @Column(name = "id_don_hang")
    private Long orderId;
    @Column(name = "id_san_pham")
    private Long productId;

}
