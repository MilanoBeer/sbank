package com.shy.sbank.domain.member.controller;

import com.shy.sbank.common.exception.BusinessException;
import com.shy.sbank.domain.member.dto.MemberRegisterDto;
import com.shy.sbank.domain.member.exception.PasswordMatchException;
import com.shy.sbank.domain.member.dto.MemberLoginDto;
import com.shy.sbank.domain.member.entity.Member;
import com.shy.sbank.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.shy.sbank.common.exception.ErrorCode.FAIL;
import static com.shy.sbank.common.exception.ErrorCode.NOT_MATCHED_PASSWORD;

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
            throw new BusinessException(FAIL);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Validated MemberLoginDto dto) {
        try {
            // email로 member찾아오기
            Member loginMember = memberService.login(dto);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (IllegalArgumentException | PasswordMatchException e) {
            throw new BusinessException(NOT_MATCHED_PASSWORD);
        }
    }
}
