package com.alkemy.java.service.impl;

import com.alkemy.java.dto.UserDtoRequest;
import com.alkemy.java.dto.UserDtoResponse;
import com.alkemy.java.model.User;
import com.alkemy.java.model.UserDetail;
import com.alkemy.java.repository.RoleRepository;
import com.alkemy.java.repository.UserRepository;
import com.alkemy.java.service.IUserService;
import org.apache.maven.artifact.repository.Authentication;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
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

    @Autowired
    private RoleRepository roleRepository;

    @Value("error.email.registered")
    private String errorPath;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder,
                           MessageSource messageSource,
                           PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager,
                           ModelMapper mapper,
                           RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.messageSource = messageSource;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.mapper = mapper;
        this.roleRepository = roleRepository;
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
        user.setRole(roleRepository.findById(userDto.getIdRole()).get());
        User newUser = userRepository.save(user);

        return UserDtoResponse.userToDto(newUser);
    }

    private UserDtoRequest mapToDTO(User user) {
        return mapper.map(user, UserDtoRequest.class);
    }

    private User mapToEntity(UserDtoRequest userDto) {
        return mapper.map(userDto, User.class);
    }

    public boolean validedRole(Long id, UserDetail userito) {

        User user = userRepository.findByEmail(userito.getUsername());
        if (user.getId().equals(id) || user.getRole().getName().equals("ROLE_USER") ){
            return true;
        }
        return false;
    }
}
