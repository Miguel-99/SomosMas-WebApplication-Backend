package com.alkemy.java.controller;

import com.alkemy.java.dto.UserDtoList;
import com.alkemy.java.repository.UserRepository;
import com.alkemy.java.service.IUserService;
import com.alkemy.java.config.Config;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(UserController.class)
@Import(Config.class)
@WithMockUser(authorities = "ROLE_ADMIN")
class UserControllerTest {

    @MockBean
    private IUserService userService;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void updateUser() {
    }

    @Test
    void delete() {
    }

    @Test
    void given2Users_whenGetAll_thenReturn2UsersArrayJson() throws Exception{
        UserDtoList user1 = new UserDtoList();
        user1.setFirstName("Tom");
        UserDtoList user2 = new UserDtoList();
        user2.setFirstName("Jerry");
        doReturn(Arrays.asList(user1,user2)).when(userService).findAllUsers();

        mockMvc.perform(get("/users/list"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect( jsonPath("$[0].firstName", is("Tom")))
        .andExpect( jsonPath("$[1].firstName", is("Jerry")));
    }
}