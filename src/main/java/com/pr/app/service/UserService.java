package com.pr.app.service;

import com.pr.app.shared.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDTO createUser(UserDTO userDTO);

    UserDTO getUser(String email);

    UserDTO getUserByUserId(String id);
}
