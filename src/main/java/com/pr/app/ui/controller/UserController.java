package com.pr.app.ui.controller;

import com.pr.app.service.UserService;
import com.pr.app.shared.dto.UserDTO;
import com.pr.app.ui.model.request.UserDetailsRequestModel;
import com.pr.app.ui.model.response.UserRest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public String getUserDetails() {
        return "Getting User";
    }

    @PostMapping
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) {
        UserRest userRest = new UserRest();
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userDetails, userDTO);
        UserDTO createdUser = userService.createUser(userDTO);
        BeanUtils.copyProperties(createdUser, userRest);
        return userRest;
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
