package com.example.baitapspringboot.service;

import com.example.baitapspringboot.dto.SearchProdDTO;
import com.example.baitapspringboot.model.Product;
import com.example.baitapspringboot.repo.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getProductByCustom(Product searchProd) {
        ExampleMatcher caseInsensitiveExampleMatcher = ExampleMatcher.matchingAny().withIgnoreCase()
                .withMatcher("price", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("productCode", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
        return productRepository.findAll(Example.of(searchProd, caseInsensitiveExampleMatcher));
    }

    public List<Product> search(SearchProdDTO dto) {
        return productRepository.search(dto);
    }
}
