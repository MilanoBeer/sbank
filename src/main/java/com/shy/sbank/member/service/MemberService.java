package com.shy.sbank.member.service;

import com.shy.sbank.member.dto.MemberLoginDto;
import com.shy.sbank.member.dto.MemberRegisterDto;
import com.shy.sbank.member.entity.Member;
import com.shy.sbank.member.exception.PasswordMatchException;
import com.shy.sbank.member.repository.MemberRepository;
import com.shy.sbank.member.repository.MemberRepository_old;
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
    // Service는 repository와 인접해있는 계층
    private final MemberRepository memberRepository;

    public boolean register(MemberRegisterDto dto) {
        // TODO : 이메일 중복 처리 ( "이미 가입된 이메일이 존재합니다" )
        if (memberRepository.existsByEmail(dto.getEmail())) throw new IllegalStateException("이미 가입된 정보가 존재합니다.");

        Member member = Member.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt()))// password, salt
                .build();

        memberRepository.save(member);
        // TODO : return true? mebmer의 id?
        return true;
    }

    public Member login(MemberLoginDto dto) {
        // 1. login정보 확인하기 위해, dto의 email -> repo -> db에서 찾아오기
        Member loginMember = memberRepository.findByEmail(dto.getEmail())
                .orElseThrow(()-> new EntityNotFoundException("등록되지 않은 사용자입니다."));

        // 2. 이 loginMember의 비밀번호랑 입력받은 dto의 비밀번호 비교
        if(!BCrypt.checkpw(dto.getPassword(), loginMember.getPassword()))
            throw new PasswordMatchException("비밀번호가 틀립니다.");
        else {
            return loginMember;
        }

        // 나중에 토큰추가
    }
}
