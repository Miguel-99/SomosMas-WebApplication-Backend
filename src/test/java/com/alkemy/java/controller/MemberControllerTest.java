package com.alkemy.java.controller;


import com.alkemy.java.dto.MemberRequestDto;
import com.alkemy.java.service.impl.MemberServiceimpl;
import com.alkemy.java.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Pageable;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

//@WebMvcTest(MemberController.class)
//@WithMockUser(authorities = "ROLE_ADMIN")
class MemberControllerTest {


    @Mock
    private MemberServiceimpl memberService;

    // it will inject the memberController and then it will inject
    // mocks like memberservice into the membercontroller
    @InjectMocks
    private MemberController controller;

    private MemberRequestDto dto;


    @BeforeEach
    void setUp() {
        dto = new MemberRequestDto();
        dto.setName("name");
        dto.setImage("image");
        dto.setDescription("desc");
        dto.setFacebookUrl("fb");
        dto.setInstagramUrl("ig");
        dto.setLinkedinUrl("linkedin");

    }

    @Test
    void getAllMembers() {
        when(memberService.getAllMembersPageable(any())).thenReturn(null);
    }

    @Test
    void createMembers() {
    }

    @Test
    void updateMember() {
    }

    @Test
    void deleteMemberById() {
    }
}