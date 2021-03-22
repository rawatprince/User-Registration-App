package com.pr.app.ui.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    @GetMapping
    public String getUserDetails() {
        return "Getting User";
    }

    @PostMapping
    public String createUser() {
        return "Creating User";
    }

    @PutMapping
    public String updateUser() {
        return "Updating User";
    }

    @DeleteMapping
    public String deleteUser() {
        return "Deleting User";
    }

}
