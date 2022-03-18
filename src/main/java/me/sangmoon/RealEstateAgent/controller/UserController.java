package me.sangmoon.RealEstateAgent.controller;

import lombok.RequiredArgsConstructor;
import me.sangmoon.RealEstateAgent.domain.User;
import me.sangmoon.RealEstateAgent.dto.UserDto;
import me.sangmoon.RealEstateAgent.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(
            @Valid @RequestBody UserDto userDto
    ) {
        return ResponseEntity.ok(userService.signup(userDto));
    }
}
