package com.example.baitapspringboot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "gio_hang_chi_tiet")
public class CartDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nguoi_tao")
    private String createdBy;
    @Column(name = "ngay_tao")
    private String createAt;
    @Column(name = "nguoi_sua")
    private String updateBy;
    @Column(name = "ngay_sua")
    private String updateAt;
    @Column(name = "trang_thai")
    private Long status;
    @Column(name = "id_gio_hang")
    private Long cartId;
    @Column(name = "id_san_pham")
    private Long productId;
    @Column(name = "so_luong")
    private Integer quantity;

    public CartDetail(String createdBy, String createAt, String updateBy, String updateAt, Long status, Long cartId, Long productId, Integer quantity) {
        this.createdBy = createdBy;
        this.createAt = createAt;
        this.updateBy = updateBy;
        this.updateAt = updateAt;
        this.status = status;
        this.cartId = cartId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public CartDetail() {
    }
}