package com.alkemy.java.service;

import com.alkemy.java.dto.MemberDto;
import com.alkemy.java.dto.MemberRequestDto;
import com.alkemy.java.dto.MemberResponseDto;
import com.alkemy.java.model.Member;

import java.util.List;

public interface IMemberService {

    List<MemberResponseDto> getAllMembers();

    MemberResponseDto createMember(MemberRequestDto org) throws Exception;

    MemberDto updateMember(MemberDto memberDto, Long id) throws Exception;
}
