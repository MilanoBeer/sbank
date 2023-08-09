package com.shy.sbank.member.controller;

import com.shy.sbank.member.dto.MemberLoginDto;
import com.shy.sbank.member.dto.MemberRegisterDto;
import com.shy.sbank.member.entity.Member;
import com.shy.sbank.member.exception.PasswordMatchException;
import com.shy.sbank.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/members")
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Validated MemberRegisterDto dto) {
        try {
            return new ResponseEntity<>(memberService.register(dto), HttpStatus.OK);
        } catch(IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Validated MemberLoginDto dto) {
        try {
            // email로 member찾아오기
            Member loginMember = memberService.login(dto);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (IllegalArgumentException | PasswordMatchException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }
}
