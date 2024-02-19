package com.example.baitapspringboot.repo;

import com.example.baitapspringboot.dto.SearchProdDTO;
import com.example.baitapspringboot.model.Product;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategoryId(Long categoryId);

    @Query(value = """
            select * from baitap_jvsb.san_pham
            where (:#{#request.prodCode} IS NULL OR ma = :#{#request.prodCode})
            and (:#{#request.price} IS NULL OR gia = :#{#request.price})
            and (:#{#request.name} IS NULL OR ten like concat('%',:#{#request.name},'%'))
            """, nativeQuery = true)
    List<Product> search(SearchProdDTO request);
}