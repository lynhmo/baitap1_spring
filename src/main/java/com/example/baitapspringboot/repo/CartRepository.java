package com.example.baitapspringboot.repo;

import com.example.baitapspringboot.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    @Transactional
    @Modifying
    @Query("delete from Cart c where c.userId = ?1")
    void deleteByUserId(Long userId);

    Optional<Cart> findByUserId(Long userId);
}