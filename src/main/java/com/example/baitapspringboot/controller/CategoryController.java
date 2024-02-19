package com.example.baitapspringboot.controller;

import com.example.baitapspringboot.dto.CommonDTO;
import com.example.baitapspringboot.dto.res.CategoryResDTO;
import com.example.baitapspringboot.model.Category;
import com.example.baitapspringboot.repo.CategoryRepository;
import com.example.baitapspringboot.repo.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    @PostMapping
    public CommonDTO insert(@RequestBody Category category) {
        String createBy = category.getCreatedBy();
        String updateBy = category.getUpdateBy();
        String dateTime = LocalDateTime.now().format(dateFormat); // current time
        String createAt = dateTime;
        String updateAt = dateTime;
        Long status = category.getStatus();
        String description = category.getDescription();
        String name = category.getName();
        String categoryCode = UUID.randomUUID().toString();
        Object data = categoryRepository.save(new Category(createBy, createAt, updateBy, updateAt, status, categoryCode, description, name));
        return new CommonDTO(200, "SUCCESS", "Add thanh cong 1 category", data);
    }

    @GetMapping("/all")
    public CategoryResDTO getAll() {
        List<Category> data = categoryRepository.findAll();
        return new CategoryResDTO(data);
    }


    @PutMapping("/update/{id}")
    public CommonDTO updateCategory(@PathVariable Long id, @RequestBody Category category) {
        Optional<Category> res = categoryRepository.findById(id);
//        Category updateCategory = categoryRepository.findById(id)
//                .orElseThrow(() -> new NullPointerException("Category không tồn tại"));
        if (res.isPresent()) {
            Category updateCategory = res.get();
            updateCategory.setUpdateBy(category.getUpdateBy());
            String dateTime = LocalDateTime.now().format(dateFormat); // current time
            updateCategory.setUpdateAt(dateTime);
            updateCategory.setStatus(category.getStatus());
            updateCategory.setDescription(category.getDescription());
            updateCategory.setName(category.getName());
            Object data = categoryRepository.save(updateCategory);
            return new CommonDTO(200, "SUCCESS", "Update thanh cong category", data);
        } else {
//            return new CommonDTO("FAIL");
            throw new NullPointerException("Category không tồn tại");
        }
    }

    @DeleteMapping("/delete/{id}")
    public CommonDTO deleteCate(@PathVariable Long id) {
        Optional<Category> res = categoryRepository.findById(id);
        if (res.isPresent()) {
            if (productRepository.findAllByCategoryId(id).isEmpty()) {
                //If category don't have any product then we can delete it
                categoryRepository.deleteById(id);
                return new CommonDTO(200, "SUCCESS", "Successful delete 1 category");
            }
            return new CommonDTO(200, "ERR", "Có sản phẩm liên kết với Category này!");
        } else {
            throw new NullPointerException("Category không tồn tại");
        }
    }
}
