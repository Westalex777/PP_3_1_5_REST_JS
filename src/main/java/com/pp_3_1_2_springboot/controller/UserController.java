package com.pp_3_1_2_springboot.controller;

import com.pp_3_1_2_springboot.model.User;
import com.pp_3_1_2_springboot.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping()
    public String userInfo(Model model, @AuthenticationPrincipal User userAuth) {
        model.addAttribute("user", userAuth);
        return "user-info";
    }
}
