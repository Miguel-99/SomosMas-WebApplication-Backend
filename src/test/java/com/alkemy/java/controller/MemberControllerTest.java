package com.alkemy.java.controller;


import com.alkemy.java.config.Config;
import com.alkemy.java.dto.MemberDto;
import com.alkemy.java.dto.MemberRequestDto;
import com.alkemy.java.dto.MemberResponseDto;
import com.alkemy.java.dto.UserDto;
import com.alkemy.java.model.Member;
import com.alkemy.java.model.User;
import com.alkemy.java.repository.MemberRepository;
import com.alkemy.java.service.IMemberService;
import com.alkemy.java.util.UtilPagination;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(MemberController.class)
@Import(Config.class)
@WithMockUser(authorities = "ROLE_ADMIN")
class MemberControllerTest {

    @MockBean
    private UtilPagination utilPagination;

    @Autowired
    private MessageSource messageSource;

    @MockBean
    private IMemberService service;

    @MockBean
    private MemberRepository memberRepository;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Pageable pageable;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    MemberResponseDto memberTest;

    @BeforeEach
    void setUp() {

        memberTest = new MemberResponseDto();
        memberTest.setName("member1");
        memberTest.setFacebookUrl("facebook.com");
        memberTest.setInstagramUrl("instagram.com");
        memberTest.setLinkedinUrl("linkedin.com");
        memberTest.setImage("images");
        memberTest.setDescription("description");

    }

    @Test
    void getAllMembers() throws Exception {
        List<MemberResponseDto> memberList = new ArrayList<>();
        memberList.add(memberTest);
        Page<MemberDto> memberTesting = new PageImpl(memberList);

        when(service.getAllMembersPageable(any(Pageable.class))).thenReturn(memberTesting);

        mockMvc.perform(get("/members")
                        .accept(MimeTypeUtils.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("page", "0")
                        .content("0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name", is("member1")))
                .andExpect(jsonPath("$.content[0].image", is("images")))
                .andDo(print());
    }


    @Test
    void createMembers() throws Exception {
        String url = "/members";
        Member mem = new Member();
        mem.setDescription("d");
        mem.setId(2L);

//        when(service.createMember(modelMapper.map(memberTest,MemberRequestDto.class)))
//                .thenReturn(new MemberResponseDto());

//        when(service.createMember(memberTest)).thenReturn(new User());

        MemberRequestDto emp = new MemberRequestDto();//whichever data your entity class have

        emp.setDescription("d");

//        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
//                        .content(asJsonString(emp))
//                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType("application/json;charset=UTF-8"));

    }


    @Test
    void updateMember() {
    }

    @Test
    void deleteMemberById() {
    }

    private String mapToJSON(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }
}