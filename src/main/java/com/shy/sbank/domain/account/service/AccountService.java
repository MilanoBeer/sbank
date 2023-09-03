package com.shy.sbank.domain.account.service;

import com.shy.sbank.domain.account.dto.AccountRegisterRequestDto;
import com.shy.sbank.domain.account.dto.DepositAccountRequestDto;
import com.shy.sbank.domain.account.dto.GetAccountListRequestDto;
import com.shy.sbank.domain.account.dto.RemitAccountRequestDto;
import com.shy.sbank.domain.account.entity.Account;
import com.shy.sbank.domain.account.repository.AccountRepository;
import com.shy.sbank.domain.member.entity.Member;
import com.shy.sbank.domain.member.repository.MemberRepository;
import com.shy.sbank.domain.product.entity.Product;
import com.shy.sbank.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AccountService {
    private final AccountRepository accountRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    // 계좌 등록
    public boolean register(AccountRegisterRequestDto dto) {

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
        return true;
    }

    // 계좌목록 조회
    public List<Account> getAccountList(GetAccountListRequestDto dto) {
        Member member = memberRepository.findById(dto.getMemberId())
                .orElseThrow(()-> new EntityNotFoundException("해당 사용자가 존재하지 않습니다."));

        // TODO : CustomException 만들기: 빈 리스트일때 처리하기 / dto를 따로 파서 / 리스트의 길이 / BaseDto 생성 -> 상속 /
        return accountRepository.findAllByMemberId(member.getId());
    }

    // 사용자 본인계좌 입금
    public boolean depositAccount(DepositAccountRequestDto dto) {
        // TODO : 인덱스 타냐 안타냐 -> 조회성능
        Account account = accountRepository.findByIdAndMemberId(dto.getAccountId(), dto.getMemberId());
        account.updateBalance(account.getBalance() + dto.getMoney());

        accountRepository.save(account);
        return true;
    }

    // A멤버 -> B멤버의 account에 대한 입금
    public boolean remitMoney(RemitAccountRequestDto dto) {
        Account senderAccount = accountRepository.findByIdAndMemberId(dto.getSenderAccountId(), dto.getSenderId());

        Account receiverAccount = accountRepository.findByIdAndMemberId(dto.getReceiverAccountId(),dto.getReceiverId());

        // 입금처리 // TODO : 돈 따라서 예외처리
        senderAccount.updateBalance(senderAccount.getBalance() - dto.getMoney());
        receiverAccount.updateBalance(receiverAccount.getBalance() + dto.getMoney());

        accountRepository.save(senderAccount);
        accountRepository.save(receiverAccount);

        // TODO : response ->
        return true;
    }

}
