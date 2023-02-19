package com.pp_3_1_5.controller;

import com.pp_3_1_5.dto.UserDto;
import com.pp_3_1_5.model.Role;
import com.pp_3_1_5.model.User;
import com.pp_3_1_5.service.role.RoleService;
import com.pp_3_1_5.service.role.RoleServiceImpl;
import com.pp_3_1_5.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("rest")
public class RestApiController {

    private final RoleService roleService;
    private final UserService userService;

    public RestApiController(RoleServiceImpl roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("users")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers().stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("roles")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @PostMapping
    public UserDto newUser(@RequestBody UserDto userDto) {
        User user = new User(userDto);
        userService.saveUser(user);
        return new UserDto(user);
    }

    @PutMapping
    public UserDto updateUser(@RequestBody UserDto userDto) {
        User user = new User(userDto);
        userService.updateUser(user);
        return new UserDto(user);
    }

    @DeleteMapping
    public HttpStatus deleteUser(@RequestBody UserDto userDto) {
        userService.deleteUser(userDto.getId());
        return  HttpStatus.CREATED;
    }
}
