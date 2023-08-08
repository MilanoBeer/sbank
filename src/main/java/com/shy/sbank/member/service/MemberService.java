package com.shy.sbank.member.service;

import com.shy.sbank.member.dto.MemberRegisterRequestDto;
import com.shy.sbank.member.entity.Member;
import com.shy.sbank.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    // Service는 repository와 인접해있는 계층
    private final MemberRepository memberRepository;

    public boolean register(MemberRegisterRequestDto dto) {
        // TODO : 이메일 중복 처리 ( "이미 가입된 이메일이 존재합니다" )

        Member member = Member.builder()
                .email(dto.getEmail())
                .password(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt()))// password, salt
                .build();

        memberRepository.save(member);

        // TODO : return true? mebmer의 id?
        return true;
    }
}
