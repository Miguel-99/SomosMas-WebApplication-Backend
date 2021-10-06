package com.alkemy.java.service.impl;

import com.alkemy.java.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
@PropertySource("classpath:messages/error.properties")
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Value("${error.user.unregister}")
    private String errorUserUnregister;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.alkemy.java.model.User user = userRepository.findByEmail(email);
        UserBuilder userBuilder = null;

        if (user != null) {
            userBuilder = User.withUsername(email);
            userBuilder.disabled(false);
            userBuilder.password(user.getPassword());
            userBuilder.authorities(new SimpleGrantedAuthority(user.getRole().getName().toUpperCase()));
        } else {
            throw new UsernameNotFoundException(errorUserUnregister);
        }
        return userBuilder.build();
    }
}
