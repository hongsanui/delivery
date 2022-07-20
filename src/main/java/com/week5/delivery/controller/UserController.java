package com.week5.delivery.controller;


import com.week5.delivery.domain.User;
import com.week5.delivery.dto.UserDto;
import com.week5.delivery.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @GetMapping("/user")
    public List<User> userList(){
        return userService.userList();
    }

    @PostMapping("/user/signup")
    public User signup(@RequestBody UserDto userDto){
        return userService.signUp(userDto);
    }

    @PostMapping("/user/login")
    public void login(){
        System.out.println("로그인");
    }

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public String registerUser(UserDto userDto) {
        userService.registerUser(userDto);
        return "redirect:/user/loginView";
    }
}
