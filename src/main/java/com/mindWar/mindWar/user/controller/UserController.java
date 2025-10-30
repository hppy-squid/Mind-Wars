package com.mindWar.mindWar.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindWar.mindWar.user.dto.UserDto;
import com.mindWar.mindWar.user.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

@GetMapping("/getUser")
public ResponseEntity<UserDto> getUser(@RequestParam Long id) {
   UserDto userdto = userService.getUserById(id);
    return ResponseEntity.ok(userdto);
}

@PostMapping("/createUser")
public ResponseEntity<UserDto> CreateUser(@RequestBody UserDto userDto) {

    UserDto createdUser = userService.createUser(userDto);
    
    return ResponseEntity.ok(createdUser);
}

@PostMapping("/login")
public ResponseEntity<UserDto> login(@RequestBody UserDto userDto) {
    UserDto loginUser = userService.login(userDto);
    return ResponseEntity.ok(loginUser);
}

@PostMapping("/logout")
public ResponseEntity<String> logout(@RequestBody UserDto userDto) {
    userService.logout(userDto);
    return ResponseEntity.ok("User logged out successfully");
}



    
}
