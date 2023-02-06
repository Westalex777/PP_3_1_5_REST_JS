package com.pp_3_1_2_springboot.controllers;

import com.pp_3_1_2_springboot.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping()
    public String userInfo(Model model) {
        model.addAttribute("user", userService.getAuthenticationUser());
        return "user-info";
    }
}
