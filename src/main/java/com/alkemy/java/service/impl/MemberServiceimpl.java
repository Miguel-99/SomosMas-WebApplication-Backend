package com.alkemy.java.service.impl;

import com.alkemy.java.dto.MemberRequestDto;
import com.alkemy.java.dto.MemberResponseDto;
import com.alkemy.java.exception.ResourceNotFoundException;
import com.alkemy.java.model.Member;
import com.alkemy.java.model.Testimonial;
import com.alkemy.java.repository.MemberRepository;
import com.alkemy.java.service.IMemberService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class MemberServiceimpl implements IMemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MessageSource messageSource;

    @Value("error.member.name.repeated")
    private String errorPath;

    @Value("error.member.id.not.found")
    private String idNotFoundMessage;


    @Override
    public List<MemberResponseDto> getAllMembers() {
        List<Member> members = memberRepository.findAll();
        return members.stream().map(member -> modelMapper.map(member, MemberResponseDto.class)).collect(Collectors.toList());
    }

    @Override
    public MemberResponseDto createMember(MemberRequestDto request) throws Exception {

        if (memberRepository.findByName(request.getName()) != null)
            throw new RuntimeException(messageSource.getMessage(errorPath, null, Locale.getDefault()));

        Member updatedMember =
                MemberRequestDto.dtoToMember(request);

        Member member = memberRepository.save(updatedMember);

        return MemberResponseDto.memberToDto(member);
    }

    public void deleteById(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(messageSource.getMessage
                        (idNotFoundMessage, null, Locale.getDefault())));
        memberRepository.delete(member);
    }


}
