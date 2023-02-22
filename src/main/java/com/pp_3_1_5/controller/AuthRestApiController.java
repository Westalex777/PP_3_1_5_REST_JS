package com.pp_3_1_5.controller;

import com.pp_3_1_5.dto.UserDto;
import com.pp_3_1_5.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthRestApiController {

    @GetMapping("api/auth")
    public ResponseEntity<UserDto> userAuth(@AuthenticationPrincipal User userAuth) {
        return ResponseEntity.ok(new UserDto(userAuth));
    }
}
