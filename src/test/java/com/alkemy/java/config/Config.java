package com.alkemy.java.config;

import com.alkemy.java.service.impl.UserDetailsServiceImpl;
import com.alkemy.java.util.JwtUtil;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class Config {
    @Bean
    public UserDetailsServiceImpl userService(){
        return Mockito.mock(UserDetailsServiceImpl.class);
    }

    @Bean
    public JwtUtil jwt(){
        return Mockito.mock(JwtUtil.class);
    }

}
