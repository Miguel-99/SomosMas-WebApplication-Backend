package com.alkemy.java.service.impl;

import com.alkemy.java.dto.UserDtoRequest;
import com.alkemy.java.dto.UserDtoResponse;
import com.alkemy.java.exception.ForbiddenException;
import com.alkemy.java.model.User;
import com.alkemy.java.repository.RoleRepository;
import com.alkemy.java.repository.UserRepository;
import com.alkemy.java.service.IUserService;
import com.alkemy.java.util.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Locale;

@Service
@PropertySource("classpath:messages/error.properties")
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Value("${error.email.registered}")
    private String errorPath;

    @Value("${error.service.user.forbidden}")
    private String errorForbiddenUser;

    @Autowired
    MessageSource messageSource;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder,
                           PasswordEncoder passwordEncoder,
                           ModelMapper mapper,
                           RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.passwordEncoder = passwordEncoder;
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

    @Override
    public boolean validedRole(Long id, String token) {
        final String ADMIN = "ROLE_ADMIN";
        String email = jwtUtil.extractUsername(token);
        User user = userRepository.findByEmail(email);

        if (!(user.getId().equals(id) || user.getRole().getName().equals(ADMIN)) ) {
            throw new ForbiddenException(messageSource.getMessage(errorForbiddenUser, null, Locale.getDefault()));
        }
        return true;
    }
}
