package com.example.baitapspringboot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "gio_hang")
public class Cart {
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
    //    =====================
    @Column(name = "id_nguoi_dung")
    private Long userId;

    public Cart(String createdBy, String createAt, String updateBy, String updateAt, Long status, Long userId) {
        this.createdBy = createdBy;
        this.createAt = createAt;
        this.updateBy = updateBy;
        this.updateAt = updateAt;
        this.status = status;
        this.userId = userId;
    }

    public Cart() {
    }
}