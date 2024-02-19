package com.example.baitapspringboot.repo;

import com.example.baitapspringboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}