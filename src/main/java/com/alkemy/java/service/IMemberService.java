package com.alkemy.java.service;

import com.alkemy.java.dto.MemberResponseDto;

import java.util.List;

public interface IMemberService {

    List<MemberResponseDto> getAllMembers();
}
