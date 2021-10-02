package com.alkemy.java.service.impl;

import com.alkemy.java.dto.UserDto;
import com.alkemy.java.model.User;
import com.alkemy.java.repository.UserRepository;
import com.alkemy.java.service.IUserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Locale;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    private MessageSource messageSource;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ModelMapper mapper;


    @Override
    public UserDto registerUser(UserDto userDto) {
        if (userRepository.findByEmail(userDto.getEmail()) != null)
            throw new RuntimeException(messageSource.getMessage("user.error.email.registered", null, Locale.getDefault()));

        User user = mapToEntity(userDto);
        user.setCreationDate(new Date());
        user.setLastUpdate(new Date());
        user.setPhoto(RandomStringUtils.random(10));
        User newUser = userRepository.save(user);
        return mapToDTO(newUser);
    }

    private UserDto mapToDTO(User user) {
        UserDto userDto = mapper.map(user, UserDto.class);
        return userDto;
    }

    private User mapToEntity(UserDto userDto) {
        User user = mapper.map(userDto, User.class);
        return user;
    }
}
