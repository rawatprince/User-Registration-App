package com.pr.app.ui.controller;

import com.pr.app.exceptions.UserServiceException;
import com.pr.app.service.UserService;
import com.pr.app.shared.dto.UserDTO;
import com.pr.app.ui.model.request.UserDetailsRequestModel;
import com.pr.app.ui.model.response.ErrorMessages;
import com.pr.app.ui.model.response.UserRest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping(path = "/{id}")
    public UserRest getUserDetails(@PathVariable String id) {
        UserRest userRest = new UserRest();
        UserDTO userDTO = userService.getUserByUserId(id);
        BeanUtils.copyProperties(userDTO, userRest);
        return userRest;
    }

    @PostMapping()
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) throws UserServiceException {

        if (userDetails.getFirstName().isEmpty() || userDetails.getLastName().isEmpty()
                || userDetails.getEmail().isEmpty() || userDetails.getPassword().isEmpty())
            throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

        UserRest userRest = new UserRest();
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userDetails, userDTO);
        UserDTO createdUser = userService.createUser(userDTO);
        BeanUtils.copyProperties(createdUser, userRest);
        return userRest;
    }

    @PutMapping("/{id}")
    public UserRest updateUser(@RequestBody UserDetailsRequestModel userDetails, @PathVariable String id) {

        UserRest userRest = new UserRest();
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userDetails, userDTO);
        UserDTO updateUser = userService.updateUser(id, userDTO);
        BeanUtils.copyProperties(updateUser, userRest);
        return userRest;

    }

    @DeleteMapping
    public String deleteUser() {
        return "Deleting User";
    }

}
