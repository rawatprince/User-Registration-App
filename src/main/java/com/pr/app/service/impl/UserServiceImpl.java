package com.pr.app.service.impl;

import com.pr.app.io.entity.UserEntity;
import com.pr.app.io.repository.UserRepository;
import com.pr.app.service.UserService;
import com.pr.app.shared.dto.UserDTO;
import com.pr.app.shared.utils.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Utils utils;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDTO createUser(UserDTO userDTO) {

        if (userRepository.findByEmail(userDTO.getEmail()) != null)
            throw new RuntimeException("Record already exist(s)");
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDTO, userEntity);
        String publicUserId = utils.generateUserId(30);
        userEntity.setUserId(publicUserId);
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        UserEntity storedUserDetails = userRepository.save(userEntity);
        UserDTO returnValue = new UserDTO();
        BeanUtils.copyProperties(storedUserDetails, returnValue);
        return returnValue;
    }

    @Override
    public UserDTO getUser(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null)
            throw new UsernameNotFoundException(email);
        UserDTO returnValue = new UserDTO();
        BeanUtils.copyProperties(userEntity, returnValue);
        return returnValue;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null)
            throw new UsernameNotFoundException(email);
        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
    }
}
