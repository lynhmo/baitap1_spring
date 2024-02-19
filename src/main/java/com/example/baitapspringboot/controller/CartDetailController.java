package com.example.baitapspringboot.controller;

import com.example.baitapspringboot.dto.CommonDTO;
import com.example.baitapspringboot.dto.FailResDto;
import com.example.baitapspringboot.model.*;
import com.example.baitapspringboot.repo.CartDetailRepository;
import com.example.baitapspringboot.repo.CartRepository;
import com.example.baitapspringboot.repo.ProductRepository;
import com.example.baitapspringboot.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cart-detail")
@RequiredArgsConstructor
public class CartDetailController {

    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;


    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    private CartController cartController;

    //id => user id
    @PostMapping("/{id}")
    public CommonDTO insert(@PathVariable Long id, @RequestBody CartDetail cartDetail) {
        String dateTime = LocalDateTime.now().format(dateFormat); // current time
        // Tìm user bằng id trước
        Optional<User> checkUser = userRepository.findById(id);
        if (checkUser.isEmpty()) {
            return new FailResDto(200, "ERR", "User không tồn tại!");
        }
        String name = checkUser.get().getFirstName();


        // Sau đó tìm trong table Cart có tồn tại UserID đang có không
        // Nếu có thì thêm sản phẩm vào bảng cart_chi_tiet như bình thường
        // Nếu không thì khi thêm sản phẩm vào bảng cart_chi_tiet thì tạo luôn 1 Cart gắn userId vào luôn
        Optional<Cart> checkHasCart = cartRepository.findByUserId(id);
        if (checkHasCart.isEmpty()) {
            Cart cart = new Cart();
            cart.setCreatedBy(name); // Sau sẽ có Token chứa userID để tìm ra tên, giỏ hàng,...
            cart.setUpdateBy(name); // Hard Code
            cart.setCreateAt(dateTime);
            cart.setUpdateAt(dateTime);
            cart.setStatus(1L);
            cart.setUserId(checkUser.get().getId());
            cartRepository.save(cart);
        }

        // Lấy ra cartID nếu mà bị tạo mới
        Optional<Cart> takeOutCartIdAgain = cartRepository.findByUserId(id);
        Long cartIDCheck = takeOutCartIdAgain.get().getId();
//        Optional<CartDetail> cartDetailOptional = cartDetailRepository.findById();
        //Check xem product co ton tai hay khong
        Optional<Product> checkProduct = productRepository.findById(cartDetail.getProductId());
        Optional<CartDetail> checkExistProduct = cartDetailRepository.findByCartIdAndProductId(cartIDCheck, cartDetail.getProductId());
        if (checkProduct.isEmpty()) {
            return new CommonDTO(500, "ERR", "Product khong ton tai");
        } else if (checkExistProduct.isPresent()) {
            // Neu product da ton tai thi update lai so luong
            Integer quantity = checkExistProduct.get().getQuantity();
            Long cartDetailId = checkExistProduct.get().getId();
            cartDetailRepository.updateQuantityByProductIdAndId((cartDetail.getQuantity() + quantity), cartDetail.getProductId(), cartDetailId);
            return new CommonDTO(200, "SUCCESS", "Da cap nhat so luong product");
        }


        if (takeOutCartIdAgain.isPresent()) {
            Long cartId = takeOutCartIdAgain.get().getId();
            // Sau khi tạo 1 Cart cho user thì lúc đó thêm vào thôi
            cartDetail.setCreatedBy(name);
            cartDetail.setUpdateBy(name);
            cartDetail.setCreateAt(dateTime);
            cartDetail.setUpdateAt(dateTime);
            cartDetail.setCartId(cartId);
            Object data = cartDetailRepository.save(cartDetail);
            return new CommonDTO(200, "SUCCESS", "Thêm sản phẩm vào giỏ thành công", data);
        }

        return new CommonDTO(500, "ERR", "Internal Error");
    }


    @GetMapping
    public CommonDTO allCartDetail() {
        return new CommonDTO(200, "SUCCESS", "All cart detail", cartDetailRepository.findAll());
    }


    @GetMapping("/{userId}")
    public CommonDTO getWithUserId(@PathVariable Long userId) {
        Optional<List<CartDetail>> cartDetailList = cartDetailRepository.getAllCartDetailWithUserId(userId);
        if (cartDetailList.isPresent()) {
            List<CartDetail> data = cartDetailList.get();
            return new CommonDTO(200, "SUCCESS", "All cart detail from user", data);
        }
        return new CommonDTO(500, "ERR", "User not found");
    }
}
