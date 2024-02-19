package com.example.baitapspringboot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Setter
@Table(name = "san_pham")
@Entity
public class Product {
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
    @Column(name = "id_danh_muc_san_pham", nullable = false)
    private Long categoryId;
    @Column(name = "ma", unique = true)
    private String productCode;
    @Column(name = "mo_ta")
    private String description;
    @Column(name = "ten")
    private String name;
    @Column(name = "gia")
    private Long price;
    @Column(name = "da_ban")
    private Long sold;

    public Product(String createdBy, String createAt, String updateBy, String updateAt, Long status, Long categoryId, String productCode, String description, String name, Long price, Long sold) {
        this.createdBy = createdBy;
        this.createAt = createAt;
        this.updateBy = updateBy;
        this.updateAt = updateAt;
        this.status = status;
        this.categoryId = categoryId;
        this.productCode = productCode;
        this.description = description;
        this.name = name;
        this.price = price;
        this.sold = sold;
    }

    public Product() {
    }
}
