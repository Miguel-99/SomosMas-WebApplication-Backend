package com.alkemy.java.service.impl;

import com.alkemy.java.model.UserDetail;
import com.alkemy.java.repository.UserRepository;
import com.alkemy.java.util.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Value("error.user.unregister")
    private String errorUserUnregister;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.alkemy.java.model.User user = userRepository.findByEmail(email);
        UserBuilder userBuilder = null;

        if (user != null) {
            userBuilder = User.withUsername(email);
            userBuilder.disabled(false);
            userBuilder.password(user.getPassword());
            userBuilder.authorities(new SimpleGrantedAuthority(/*"ROLE_" + Roles.CLIENT*/ user.getRole().getName().toUpperCase()));
        } else {
            throw new UsernameNotFoundException(errorUserUnregister);
        }
        //return userBuilder.build();
        return new UserDetail(userRepository.findByEmail(email));
    }
}
