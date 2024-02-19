package com.example.baitapspringboot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.io.Serializable;

@Getter
@Setter
@Table(name = "danh_muc_san_pham")
@Entity
public class Category {
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
    @Column(name = "ma", unique = true)
    private String categoryCode;
    @Column(name = "mo_ta")
    private String description;
    @Column(name = "ten")
    private String name;

    public Category(String createdBy, String createAt, String updateBy, String updateAt, Long status, String categoryCode, String description, String name) {
        this.createdBy = createdBy;
        this.createAt = createAt;
        this.updateBy = updateBy;
        this.updateAt = updateAt;
        this.status = status;
        this.categoryCode = categoryCode;
        this.description = description;
        this.name = name;
    }

    public Category() {

    }
}
