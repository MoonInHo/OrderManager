package com.foodtech.mate.service;

import com.foodtech.mate.domain.dto.AccountDto;
import com.foodtech.mate.domain.dto.CertificationDto;
import com.foodtech.mate.domain.entity.Account;
import com.foodtech.mate.repository.MemberQueryRepository;
import com.foodtech.mate.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@DisplayName("[유닛 테스트] - 회원 서비스")
public class MemberServiceTest {

    private final MemberRepository memberRepository = mock(MemberRepository.class);
    private final MemberQueryRepository memberQueryRepository = mock(MemberQueryRepository.class);
    private final MemberService memberService = new MemberService(memberRepository, memberQueryRepository);

    @Test
    @DisplayName("회원 가입 - 이메일 중복시 예외 발생")
    void existsEmail_signUp_throwException() {
        //given
        AccountDto accountDto = AccountDto.createAccountDto("test123", "testPassword123!", "010-1234-5678");
        Account account = AccountDto.createAccount(accountDto);
        given(memberQueryRepository.isUsernameExist(any())).willReturn(true);

        //when
        Throwable throwable = catchThrowable(() -> memberService.signUp(account));

        //then
        assertThat(throwable).isInstanceOf(IllegalStateException.class);
        assertThat(throwable).hasMessage("! 이미 사용중인 아이디 입니다.");
    }

    @Test
    @DisplayName("회원 가입 - 올바른 회원정보로 가입시 회원생성")
    void properInfo_signUp_createMember() {
        //given
        AccountDto accountDto = AccountDto.createAccountDto("test123", "testPassword123!", "010-1234-5678");
        Account account = AccountDto.createAccount(accountDto);
        given(memberRepository.save(any())).willReturn(account);

        // when
        Account createdAccount = memberRepository.save(account);

        //then
        assertThat(createdAccount).isNotNull();
        assertThat(createdAccount).isInstanceOf(Account.class);
    }

    @Test
    @DisplayName("회원 인증 - 인증 번호 생성 요청시 무작위 인증번호 반환")
    void requestCreateCertificationCode_returnRandomCertificationCode() {
        //given & when
        String certificationCode = memberService.createCertificationCode();

        //then
        assertThat(certificationCode).isNotNull();
    }

    @Test
    @DisplayName("회원 인증 - 올바른 연락처로 회원 아이디 찾기 요청시 조회된 아이디 반환")
    void properPhone_findUsername_returnFoundUsername() throws Exception {
        //given
        CertificationDto certificationDto = CertificationDto.createCertificationDto("010-1234-5678", null);
        AccountDto accountDto = AccountDto.createAccountDto("test123", "testPassword123!", "010-1234-5678");
        Account account = AccountDto.createAccount(accountDto);
        given(memberQueryRepository.findByUsername(any())).willReturn(account);

        //when
        Account foundAccount = memberQueryRepository.findByUsername(certificationDto.getPhone());

        //then
        assertThat(foundAccount).isNotNull();
        assertThat(foundAccount).isInstanceOf(Account.class);
    }
}
