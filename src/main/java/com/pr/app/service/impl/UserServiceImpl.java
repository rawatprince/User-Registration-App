package com.pr.app.service.impl;

import com.pr.app.io.entity.UserEntity;
import com.pr.app.io.repository.UserRepository;
import com.pr.app.service.UserService;
import com.pr.app.shared.dto.UserDTO;
import com.pr.app.shared.utils.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Utils utils;

    @Override
    public UserDTO createUser(UserDTO userDTO) {

        if (userRepository.findByEmail(userDTO.getEmail()) != null)
            throw new RuntimeException("Record already exist(s)");
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDTO, userEntity);
        String publicUserId = utils.generateUserId(30);
        userEntity.setUserId(publicUserId);
        userEntity.setEncryptedPassword("test");
        UserEntity storedUserDetails = userRepository.save(userEntity);
        UserDTO returnValue = new UserDTO();
        BeanUtils.copyProperties(storedUserDetails, returnValue);
        return returnValue;
    }
}
