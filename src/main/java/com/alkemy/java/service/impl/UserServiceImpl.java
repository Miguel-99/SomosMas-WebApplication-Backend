package com.alkemy.java.service.impl;

import com.alkemy.java.dto.UserDto;
import com.alkemy.java.dto.UserDtoRequest;
import com.alkemy.java.dto.UserDtoResponse;
import com.alkemy.java.exception.ResourceNotFoundException;
import com.alkemy.java.model.User;
import com.alkemy.java.repository.UserRepository;
import com.alkemy.java.service.IEmailService;
import com.alkemy.java.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
@Slf4j
@PropertySource("classpath:messages/error.properties")
@Service
@PropertySource("classpath:messages/messages.properties")
public class UserServiceImpl implements IUserService {

    UserRepository userRepository;

    BCryptPasswordEncoder bCryptPasswordEncoder;

    private MessageSource messageSource;

    private PasswordEncoder passwordEncoder;

    private AuthenticationManager authenticationManager;

    private ModelMapper mapper;

    @Value("${error.user.dont.exist}")
    private String resourceNotFound;

    @Value("error.email.registered")
    private String errorPath;

    @Value("${sendgrid.subject.welcome}")
    private String welcome;

    @Autowired
    IEmailService emailService;

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
            throw new RuntimeException(messageSource.getMessage(errorPath, null, Locale.getDefault()));

        User user = mapToEntity(userDto);

        user.setCreationDate(new Date());
        user.setLastUpdate(new Date());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        user.setPhoto("url1");
        User newUser = userRepository.save(user);

        emailService.sendEmailWithTemplate(userDto,welcome);

        return UserDtoResponse.userToDto(newUser);
    }

    @Override
    public UserDto updateUser(Long userId, UserDto userDto) {
        boolean userExists = userRepository.existsById(userId);
        log.info("user with id: "+ userId +" exists? " + userExists);
        if (!userExists)
            throw new ResourceNotFoundException(resourceNotFound);
        User user = userRepository.getOne(userId);

        if (userDto.getFirstName() != null)
            user.setFirstName(userDto.getFirstName());
        if (userDto.getLastName() != null)
            user.setLastName(userDto.getLastName());
        if (userDto.getEmail() != null)
            user.setEmail(userDto.getEmail());
        if (userDto.getPassword() != null)
            user.setPassword(userDto.getPassword());
        if (userDto.getPhoto() != null)
            user.setPhoto(userDto.getPhoto());
        if (userDto.getRole() != null)
            user.setRole(userDto.getRole());
        if (userDto.getDeleted() != null)
            user.setDeleted(userDto.getDeleted());
        user.setLastUpdate(new Date());

        user = userRepository.save(user);
        return mapper.map(user, UserDto.class);
    }

    private UserDtoRequest mapToDTO(User user) {
        return mapper.map(user, UserDtoRequest.class);
    }

    private User mapToEntity(UserDtoRequest userDto) {
        return mapper.map(userDto, User.class);
    }
}
