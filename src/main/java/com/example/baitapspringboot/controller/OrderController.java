package com.example.baitapspringboot.controller;

import com.example.baitapspringboot.dto.*;
import com.example.baitapspringboot.model.*;
import com.example.baitapspringboot.repo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final UserRepository userRepository;
    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");


    @PostMapping("/{userId}")
    public RespondDTO method(@PathVariable Long userId) {
        // Tim cart theo UserId
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new NullPointerException("User khong co gio hang nay"));
        // Check user và lấy ra user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NullPointerException("Không co user nay"));

        Optional<OrderSumDto> orderTotalPrice = cartDetailRepository.totalOrderPrice(cart.getId());
        Long totalPrice = orderTotalPrice
                .orElseThrow(() -> new NullPointerException("Không co user nay")).getTotalPrice();

        // lay cac du lieu cua user ra
        String name = user.getFirstName();
        String lastName = user.getLastAndMiddleName();
        String address = user.getAddress();
        String phone = user.getPhone();
        String dateTime = LocalDateTime.now().format(dateFormat); // current time
        // bind
        Order order = new Order();
        order.setUserId(userId);
        order.setLastAndMiddleName(lastName);
        order.setName(name);
        order.setAddress(address);
        order.setPhone(phone);
        order.setTotalPrice(totalPrice);
        order.setDescription("test1");
        order.setStatus(1L);
        order.setOrderCode(UUID.randomUUID().toString());
        order.setCreateAt(dateTime);
        order.setUpdateAt(dateTime);
        order.setCreatedBy(name);
        order.setUpdateBy(name);
        // save 1 bản ghi order
        orderRepository.save(order);

        // Lấy ra orderId vừa tạo. Lấy bằng orderCode
        Long orderId = orderRepository.findByOrderCode(order.getOrderCode())
                .orElseThrow(() -> new NullPointerException("Khong ton tai order")).getId();

        // List product trong cartDetail
        List<OrderDetailProductDto> allProd = cartDetailRepository
                .allProductByCartId(cart.getId())
                .orElseThrow(() -> new NullPointerException("idk bruh"));
        List<OrderDetail> orderDetailList = new ArrayList<>();
        // Loop qua cac san pham rồi add vào list
        for (OrderDetailProductDto product : allProd) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setProductId(product.getId_prod());
            orderDetail.setQuantity(product.getQuantity());
            orderDetail.setOrderId(orderId);
            orderDetailList.add(orderDetail);
            System.out.println("Check");
        }
        // Sau khi list hoàn thành thì save vào  DB
        orderDetailRepository.saveAll(orderDetailList);

        // Hoàn thành thêm order và order_detail
        // Xóa Cart và Cart Detail
        // 1. Xóa Cart Detail trước. Xóa bằng CartID
        cartDetailRepository.deleteByCartId(cart.getId());
        // 2. Xóa Cart sau. Xóa bằng UserID (vì 1 user chỉ có 1 cart)
        cartRepository.deleteByUserId(userId);

        return new RespondDTO("Tao Order thanh cong", "SUCCESS", 200);
    }


    @GetMapping("/{cartId}")
    public CommonDTO method111(@PathVariable Long cartId) {
//        List<Long[]> objectList = cartDetailRepository.testTotalOrderPrice(cartId).orElseThrow();
//        Long[] longList = objectList.get(0);
//        Long check = longList[1];
//        return new CommonDTO(200, "SUCCESS", "Take out data", objectList);

        Optional<OrderSumDto> orderSumDto = cartDetailRepository.custom(cartId);
        return new CommonDTO(200, "SUCCESS", "Take out data", orderSumDto.orElseThrow());

    }


    @GetMapping("/all")
    public List<Order> method222() {
        return orderRepository.findAll();
    }
}




