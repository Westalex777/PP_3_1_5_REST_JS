package com.pp_3_1_2_springboot.controllers;

import com.pp_3_1_2_springboot.model.Role;
import com.pp_3_1_2_springboot.model.User;
import com.pp_3_1_2_springboot.service.user.UserService;
import com.pp_3_1_2_springboot.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserControllers {

    private final UserService userService;
    private final RoleService roleService;


    @Autowired
    public UserControllers(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("user")
    public String userInfo(Model model) {
        model.addAttribute("user", userService.getAuthenticationUser());
        return "user-info";
    }

    @GetMapping
    public String getAllUser(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("login", userService.getAuthenticationUser().getEmail());
        return "index";
    }

    @GetMapping("userCreate")
    public String userCreate(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        return "user-create";
    }

    @PostMapping("addUser")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.setRoles(user);
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("deleteUser")
    public String deleteUser(@RequestParam("id") int id) {
        userService.deleteUser(id);
        return "redirect:/";
    }

    @GetMapping("updateUserForm")
    public String updateUserForm(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "user-update";
    }

    @PostMapping("updateUser")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.setRoles(user);
        userService.updateUser(user);
        return "redirect:/";
    }
}
