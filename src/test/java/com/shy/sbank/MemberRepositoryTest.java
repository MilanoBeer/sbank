package com.shy.sbank;


import com.shy.sbank.domain.member.entity.Member;
import com.shy.sbank.domain.member.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void testMember() {
        // given
        Member member = Member.builder()
                .name("혜연")
                .email("hy@naver.com")
                .password("1234123412")
                .build();

        memberRepository.save(member);

        // TODO : 왜 findById로 하려면 안돼지..? ...????
        Member findMember = memberRepository.getById(member.getId());

        // then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getName()).isEqualTo(member.getName());
        Assertions.assertThat(findMember).isEqualTo(member);
    }
}
