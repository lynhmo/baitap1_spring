package com.example.baitapspringboot.repo;

import com.example.baitapspringboot.dto.OrderDetailProductDto;
import com.example.baitapspringboot.dto.OrderSumDto;
import com.example.baitapspringboot.model.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {
    @Transactional
    @Modifying
    @Query("delete from CartDetail c where c.cartId = ?1")
    void deleteByCartId(Long cartId);

    @Transactional
    @Modifying
    @Query("update CartDetail c set c.quantity = ?1 where c.productId = ?2 and c.id =?3")
    void updateQuantityByProductIdAndId(Integer quantity, Long productId, Long id);


    @Query("select c from CartDetail c inner join Cart g ON c.cartId = g.id where g.userId=?1")
    Optional<List<CartDetail>> getAllCartDetailWithUserId(Long id);


    @Query(""" 
            select
                new com.example.baitapspringboot.dto.OrderSumDto(c.cartId, sum(p.price*c.quantity) )
            from CartDetail c
            inner join Cart g ON c.cartId = g.id
            inner join Product p ON p.id=c.productId
            where c.cartId = ?1
            group by c.cartId
            """)
    Optional<OrderSumDto> totalOrderPrice(Long id);


    @Query("""
            select
                new com.example.baitapspringboot.dto.OrderSumDto(c.cartId, sum(p.price*c.quantity) )
            from CartDetail c
            inner join Cart g ON c.cartId = g.id
            inner join Product p ON p.id=c.productId
            where c.cartId = ?1
            group by c.cartId
            """)
    Optional<OrderSumDto> custom(Long id);

    @Query("""
            select
                new com.example.baitapspringboot.dto.OrderDetailProductDto (c.productId, c.quantity)
            from CartDetail c
            inner join Cart g ON c.cartId = g.id
            inner join Product p ON p.id=c.productId
            where c.cartId = ?1
            """)
    Optional<List<OrderDetailProductDto>> allProductByCartId(Long id);

    Optional<CartDetail> findByCartIdAndProductId(Long cartId, Long productId);


}