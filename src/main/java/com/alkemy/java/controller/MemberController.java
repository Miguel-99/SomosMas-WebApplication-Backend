package com.alkemy.java.controller;

import com.alkemy.java.dto.MemberRequestDto;
import com.alkemy.java.dto.MemberResponseDto;
import com.alkemy.java.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private IMemberService memberService;

    @Value("success.get")
    private String successGet;

    @Autowired
    private MessageSource messageSource;

    @GetMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getAllMembers(){

        List<MemberResponseDto> members = memberService.getAllMembers();

        if(members.isEmpty()){
            String message = messageSource.getMessage(successGet, null, Locale.getDefault());
            return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>(members, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<?> createMembers(@Valid @RequestBody MemberRequestDto request){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(memberService.createMember(request));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
