package com.example.baitapspringboot.controller;

import com.example.baitapspringboot.dto.CommonDTO;
import com.example.baitapspringboot.model.User;
import com.example.baitapspringboot.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    @PostMapping
    public CommonDTO insertUser(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        String firstName = user.getFirstName();
        String lastAndMiddleName = user.getLastAndMiddleName();
        String phone = user.getPhone();
        String address = user.getAddress();
        Object data = userRepository.save(new User(username, password, firstName, lastAndMiddleName, phone, address));
        return new CommonDTO(200, "SUCCESS", "Add User", data);
    }

    @GetMapping("/all")
    public CommonDTO all() {
        return new CommonDTO(200, "SUCCESS", "All user", userRepository.findAll());
    }

    @DeleteMapping("/delete/{id}")
    public CommonDTO delete(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
            return new CommonDTO(200, "SUCCESS", "Delete user", user.get());
        } else {
            return new CommonDTO(200, "ERR", "User not found");
        }
    }

    @PutMapping("/update/{id}")
    public CommonDTO update(@PathVariable Long id, @RequestBody User user) {
        Optional<User> checkUser = userRepository.findById(id);
        if (checkUser.isPresent()) {
            User updateUser = checkUser.get();
            updateUser.setUsername(user.getUsername());
            updateUser.setPassword(user.getPassword());
            updateUser.setFirstName(user.getFirstName());
            updateUser.setLastAndMiddleName(user.getLastAndMiddleName());
            updateUser.setPhone(user.getPhone());
            updateUser.setAddress(user.getAddress());
            Object data = userRepository.save(updateUser);
            return new CommonDTO(200, "SUCCESS", "Update user");
        } else {
            return new CommonDTO(200, "ERR", "User not found");
        }
    }
}
