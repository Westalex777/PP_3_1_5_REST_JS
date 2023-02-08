package com.pp_3_1_2_springboot.controller;

import com.pp_3_1_2_springboot.service.role.RoleService;
import com.pp_3_1_2_springboot.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Test {

    private final UserService userService;
    private final RoleService roleService;

    public Test(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("test")
    public String test() {
        return "test";
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }
}
