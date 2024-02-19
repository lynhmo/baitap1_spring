package com.example.baitapspringboot.repo;

import com.example.baitapspringboot.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}