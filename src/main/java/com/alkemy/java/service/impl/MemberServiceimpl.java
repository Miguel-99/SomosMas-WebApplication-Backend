package com.alkemy.java.service.impl;

import com.alkemy.java.dto.MemberResponseDto;
import com.alkemy.java.model.Member;
import com.alkemy.java.repository.MemberRepository;
import com.alkemy.java.service.IMemberService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberServiceimpl implements IMemberService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<MemberResponseDto> getAllMembers(){
        List<Member> members = memberRepository.findAll();
        return members.stream().map(member -> modelMapper.map(member, MemberResponseDto.class)).collect(Collectors.toList());
    }
}
