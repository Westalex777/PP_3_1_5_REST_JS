package com.pp_3_1_5.controller;

import com.pp_3_1_5.dto.UserDto;
import com.pp_3_1_5.model.User;
import com.pp_3_1_5.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/admin/users")
public class RestApiController {

    private final UserService userService;

    public RestApiController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> usersDto =  userService.getAllUsers()
                .stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
        return new ResponseEntity<>(usersDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDto> newUser(@RequestBody UserDto userDto) {
        User user = new User(userDto);
        userService.saveUser(user);
        return new ResponseEntity<>(new UserDto(user), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        User user = new User(userDto);
        userService.updateUser(user);
        return new ResponseEntity<>(new UserDto(user), HttpStatus.OK);
    }

    @DeleteMapping
    public HttpStatus deleteUser(@RequestBody UserDto userDto) {
        userService.deleteUser(userDto.getId());
        return HttpStatus.OK;
    }
}