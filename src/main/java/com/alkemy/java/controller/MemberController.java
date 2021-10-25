package com.alkemy.java.controller;

import com.alkemy.java.dto.MemberDto;
import com.alkemy.java.dto.MemberRequestDto;
import com.alkemy.java.dto.MemberResponseDto;
import com.alkemy.java.service.IMemberService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private IMemberService memberService;

    @Value("success.get")
    private String successGet;

    @Value("success.deleted")
    private String successfullyDeleted;

    @Autowired
    private MessageSource messageSource;

    @GetMapping()
    @ApiOperation("Bring all the members.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden Access"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getAllMembers(){

        List<MemberResponseDto> members = memberService.getAllMembers();

        return members.isEmpty() ? ResponseEntity.status(HttpStatus.NO_CONTENT).body(members) : ResponseEntity.ok(members);
    }

    @PostMapping
    @ApiOperation(value = "Add a new Member.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Success."),
            @ApiResponse(code = 400, message = "Bad Request."),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden Access"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<?> createMembers(@Valid @RequestBody MemberRequestDto request){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(memberService.createMember(request));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update an existing Member.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Success."),
            @ApiResponse(code = 400, message = "Bad Request."),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden Access"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<?> updateMember(@Valid @RequestBody MemberDto memberDto,@PathVariable(name = "id") Long id){
       try {
           return ResponseEntity.status(HttpStatus.OK).body(memberService.updateMember(memberDto, id));
       }catch (Exception e){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
       }
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Delete an existing Member.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success."),
            @ApiResponse(code = 404, message = "Member not found."),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden Access"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<?> deleteMemberById(@PathVariable Long id) {
        try {
            memberService.deleteById(id);

            return new ResponseEntity<>(messageSource.getMessage
                    (successfullyDeleted, null, Locale.getDefault()),
                    HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
