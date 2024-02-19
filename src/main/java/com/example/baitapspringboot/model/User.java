package com.example.baitapspringboot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String username;
    private String password;
    @Column(name = "ten")
    private String firstName;
    @Column(name = "ho_va_ten_dem")
    private String lastAndMiddleName;
    @Column(name = "sdt")
    private String phone;
    @Column(name = "dia_chi")
    private String address;

    public User(String username, String password, String firstName, String lastAndMiddleName, String phone, String address) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastAndMiddleName = lastAndMiddleName;
        this.phone = phone;
        this.address = address;
    }

    public User() {
    }
}