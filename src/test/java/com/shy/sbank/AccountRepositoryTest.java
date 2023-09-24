package com.shy.sbank;

import com.shy.sbank.domain.account.dto.AccountRegisterRequestDto;
import com.shy.sbank.domain.account.entity.Account;
import com.shy.sbank.domain.account.repository.AccountRepository;
import com.shy.sbank.domain.member.entity.Member;
import com.shy.sbank.domain.member.repository.MemberRepository;
import com.shy.sbank.domain.product.entity.Product;
import com.shy.sbank.domain.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.UUID;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;
    MemberRepository memberRepository;
    ProductRepository productRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void testAccount() {

        AccountRegisterRequestDto dto = AccountRegisterRequestDto.builder()
                .memberId(UUID.fromString("9db72df5-7ab1-4382-950e-748b133b4ccd"))
                .productId(1L)
                .accountName("테스트코드계좌이름")
                .expireYmdt("2024-09-23T18:34:24.458")
                .build();

        Member member = memberRepository.findById(dto.getMemberId())
                .orElseThrow(()-> new EntityNotFoundException("존재하지 않는 사용자입니다."));

        LocalDateTime currentYmdt = LocalDateTime.now();
        LocalDateTime expireYmdt = LocalDateTime.parse(dto.getExpireYmdt());

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new IllegalStateException("해당 상품 정보가 존재하지 않습니다."));

        Account account = Account.builder()
                .member(member)
                .accountName(dto.getAccountName())
                .createYmdt(currentYmdt)
                .expireYmdt(expireYmdt)
                .balance(0.0)
                .product(product)
                .build();
        accountRepository.save(account);

        Account savedAccount = accountRepository.findByMemberIdAndAccountName(dto.getMemberId(), dto.getAccountName());

        // then
        assertThat(accountRepository.getById(savedAccount.getId()).getMember().getId())
                .isEqualTo(dto.getMemberId());
    }

}
