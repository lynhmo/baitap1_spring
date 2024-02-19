package com.example.baitapspringboot.controller;

import com.example.baitapspringboot.dto.CommonDTO;
import com.example.baitapspringboot.dto.FailResDto;
import com.example.baitapspringboot.model.Cart;
import com.example.baitapspringboot.model.User;
import com.example.baitapspringboot.repo.CartDetailRepository;
import com.example.baitapspringboot.repo.CartRepository;
import com.example.baitapspringboot.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final UserRepository userRepository;

    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    @PostMapping
    public CommonDTO insertCart(@RequestBody Cart cart) {
        Optional<User> check = userRepository.findById(cart.getUserId());
        Optional<Cart> checkHasCart = cartRepository.findByUserId(cart.getUserId());
        // Nếu không tìm thấy cart qua user id thì mới tạo cart
        if (check.isPresent() && checkHasCart.isEmpty()) {
            String createBy = cart.getCreatedBy();
            String updateBy = cart.getUpdateBy();
            String dateTime = LocalDateTime.now().format(dateFormat); // current time
            String createAt = dateTime;
            String updateAt = dateTime;
            Long status = cart.getStatus();
            Long userId = check.get().getId();
            Object data = cartRepository.save(new Cart(createBy, createAt, updateBy, updateAt, status, userId));
            return new CommonDTO(200, "SUCCESS", "Add 1 cart", data);
        } else if (check.isEmpty()) {
            return new CommonDTO(200, "ERR", "User không ton tai ");
        } else {
            return new CommonDTO(200, "ERR", "user da co cart ");
        }
    }


    @GetMapping("/all")
    public CommonDTO all() {
        return new CommonDTO(200, "SUCCESS", "All cart", cartRepository.findAll());
    }
}
