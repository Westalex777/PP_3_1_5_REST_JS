package com.pp_3_1_2_springboot.controllers;

import com.pp_3_1_2_springboot.model.User;
import com.pp_3_1_2_springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserControllers {

    @Autowired
    private UserService userService;

    @GetMapping
    public String getAllUser(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }

    @GetMapping("userCreate")
    public String userCreate(Model model) {
        model.addAttribute("user", new User());
        return "user-create";
    }

    @PostMapping("addUser")
    public String saveUser(@ModelAttribute("user") User user) {
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
        return "user-update";
    }

    @PostMapping("updateUser")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/";
    }
}
