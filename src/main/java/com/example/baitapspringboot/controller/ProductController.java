package com.example.baitapspringboot.controller;

import com.example.baitapspringboot.dto.CommonDTO;
import com.example.baitapspringboot.dto.SearchProdDTO;
import com.example.baitapspringboot.model.Category;
import com.example.baitapspringboot.model.Product;
import com.example.baitapspringboot.repo.CategoryRepository;
import com.example.baitapspringboot.repo.ProductRepository;
import com.example.baitapspringboot.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ProductService productService;
    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    @GetMapping("/all")
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }


//    @GetMapping("/find/{data}")
//    public CommonDTO searchProd(@PathVariable String data) {
//
//    }


    @PostMapping
    public CommonDTO insert1(@RequestBody Product product) {
        Optional<Category> checkCategory = categoryRepository.findById(product.getCategoryId());
        if (checkCategory.isPresent()) {
            String createBy = product.getCreatedBy();
            String updateBy = product.getUpdateBy();
            String dateTime = LocalDateTime.now().format(dateFormat); // current time
            String createAt = dateTime;
            String updateAt = dateTime;
            Long status = product.getStatus();
            Long categoryId = product.getCategoryId();
            String description = product.getDescription();
            String name = product.getName();
            Long price = product.getPrice();
            Long sold = product.getSold();
            String productCode = UUID.randomUUID().toString();
            Object data = productRepository.save(new Product(createBy, createAt, updateBy, updateAt, status, categoryId, productCode, description, name, price, sold));
            return new CommonDTO(200, "Add 1 product", data);
        } else {
            return new CommonDTO(200, "Category không tồn tại");
        }
    }

    @PutMapping("/update/{id}")
    public CommonDTO updateCategory(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> checkProd = productRepository.findById(id);
        Optional<Category> checkCategory = categoryRepository.findById(product.getCategoryId());

        if (checkProd.isPresent() && checkCategory.isPresent()) {
            Product updateProd = checkProd.get();

            updateProd.setUpdateBy(product.getUpdateBy());
            String dateTime = LocalDateTime.now().format(dateFormat); // current time
            updateProd.setUpdateAt(dateTime);
            updateProd.setStatus(product.getStatus());
            updateProd.setCategoryId(product.getCategoryId());
            updateProd.setDescription(product.getDescription());
            updateProd.setName(product.getName());
            updateProd.setPrice(product.getPrice());
            Object data = productRepository.save(updateProd);
            return new CommonDTO(200, "SUCCESS", "Update thanh cong");
        } else if (checkProd.isEmpty()) {
            throw new NullPointerException("Product khong ton tai");
        } else {
            return new CommonDTO(200, "ERR", "Category không ton tai");
        }
    }

    @DeleteMapping("/delete/{id}")
    public CommonDTO deleteProd(@PathVariable Long id) {
        Optional<Product> res = productRepository.findById(id);
        if (res.isPresent()) {
            categoryRepository.deleteById(id);
            return new CommonDTO(200, "SUCCESS", "Xóa thành công 1 sản phẩm");
        } else {
            throw new NullPointerException("Category không tồn tại");
        }
    }


    @PostMapping("/search")
    public CommonDTO searchingAA(@RequestBody SearchProdDTO resProduct) {
//        return new CommonDTO(200, "SUCCESS", "Search Product", productService.getProductByCustom(resProduct));
        List<Product> liastProd = productService.search(resProduct);
        return new CommonDTO(200, "SUCCESS", liastProd);
    }


    @PostMapping("/search/v1")
    public CommonDTO searching1111(@RequestBody Product resProduct) {
        return new CommonDTO(200, "SUCCESS", "Search Product", productService.getProductByCustom(resProduct));
    }

}
