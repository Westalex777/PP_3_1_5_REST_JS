package com.pp_3_1_2_springboot.controller;

import com.pp_3_1_2_springboot.model.User;
import com.pp_3_1_2_springboot.service.user.UserService;
import com.pp_3_1_2_springboot.service.role.RoleService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;


    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String getAllUser(Model model, @AuthenticationPrincipal User userAuth) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("userAuth", userAuth);
        model.addAttribute("newUser", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        return "index";
    }

    @PostMapping("addUser")
    public String saveUser(@ModelAttribute("newUser") User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @PostMapping("deleteUser")
    public String deleteUser(@ModelAttribute("user") User user) {
        userService.deleteUser(user);
        return "redirect:/";
    }

    @PostMapping("updateUser")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/";
    }
}
