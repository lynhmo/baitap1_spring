package com.example.baitapspringboot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "don_hang")
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //========
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
    //========


    @Column(name = "dia_chi")
    private String address;
    @Column(name = "ma", unique = true)
    private String orderCode;
    @Column(name = "mo_ta")
    private String description;
    @Column(name = "ho_va_ten_dem")
    private String lastAndMiddleName;
    @Column(name = "ten")
    private String name;
    @Column(name = "sdt")
    private String phone;
    @Column(name = "tong_gia")
    private Long totalPrice;
    @Column(name = "id_nguoi_dung")
    private Long userId;


}
