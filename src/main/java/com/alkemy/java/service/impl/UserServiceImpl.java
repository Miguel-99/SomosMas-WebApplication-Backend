package com.alkemy.java.service.impl;

import com.alkemy.java.dto.UserDtoRequest;
import com.alkemy.java.dto.UserDtoResponse;
import com.alkemy.java.model.User;
import com.alkemy.java.repository.UserRepository;
import com.alkemy.java.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    UserRepository userRepository;

    BCryptPasswordEncoder bCryptPasswordEncoder;

    private MessageSource messageSource;

    private PasswordEncoder passwordEncoder;

    private AuthenticationManager authenticationManager;

    private ModelMapper mapper;

    @Value("error.email.registered")
    private String errorPath;

    @Value("{error.user.notFoundID}")
    private String idNotFound;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder,
                           MessageSource messageSource,
                           PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager,
                           ModelMapper mapper) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.messageSource = messageSource;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.mapper = mapper;
    }

    @Override
    public UserDtoResponse registerUser(UserDtoRequest userDto) {
        if (userRepository.findByEmail(userDto.getEmail()) != null)

            throw new RuntimeException(errorPath);

        User user = mapToEntity(userDto);
        user.setCreationDate(new Date());
        user.setLastUpdate(new Date());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        user.setPhoto("url1");
        User newUser = userRepository.save(user);

        return UserDtoResponse.userToDto(newUser);
    }


    @Override
    @Transactional
    public User delete(Long idUser) {
        Optional<User> userOld = Optional.of(userRepository.getById(idUser));
        User user;
        if (userOld.isPresent()) {
            user = userOld.get();
            userRepository.delete(user);
        } else {
            throw new NoSuchElementException(idNotFound);
        }

        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Long idUser) {
        return userRepository.findById(idUser).orElseThrow(() -> new NoSuchElementException("Movie ID was not found. By ID: " + idUser));
    }


    private UserDtoRequest mapToDTO(User user) {
        return mapper.map(user, UserDtoRequest.class);
    }

    private User mapToEntity(UserDtoRequest userDto) {
        return mapper.map(userDto, User.class);
    }
}
