package com.shy.sbank.domain.member.repository;

import com.shy.sbank.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MemberRepository extends JpaRepository<Member, UUID> {
    // 이메일 중복처리
    boolean existsByEmail(String email);

    // 이메일로 Member찾기
    Optional<Member> findByEmail(String email);
}
