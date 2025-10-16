package com.mindWar.mindWar.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/users")
public class UserController {

@GetMapping("path")
public String getUser(@RequestParam String param) {
    return new String();
}

@PostMapping("path")
public String CreateUser(@RequestBody String entity) {
    //TODO: process POST request
    
    return entity;
}



    
}
