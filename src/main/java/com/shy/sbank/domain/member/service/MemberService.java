package com.shy.sbank.domain.member.service;

import com.shy.sbank.domain.member.dto.MemberRegisterDto;
import com.shy.sbank.domain.member.exception.PasswordMatchException;
import com.shy.sbank.domain.member.repository.MemberRepository;
import com.shy.sbank.domain.member.dto.MemberLoginDto;
import com.shy.sbank.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;

    public boolean register(MemberRegisterDto dto) {
        if (memberRepository.existsByEmail(dto.getEmail())) throw new IllegalStateException("이미 가입된 정보가 존재합니다.");

        Member member = Member.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt()))// password, salt
                .build();

        memberRepository.save(member);
        return true;
    }

    public Member login(MemberLoginDto dto) {
        Member loginMember = memberRepository.findByEmail(dto.getEmail())
                .orElseThrow(()-> new EntityNotFoundException("등록되지 않은 사용자입니다."));

        if(!BCrypt.checkpw(dto.getPassword(), loginMember.getPassword()))
            throw new PasswordMatchException("비밀번호가 틀립니다.");
        else {
            return loginMember;
        }
    }
}
