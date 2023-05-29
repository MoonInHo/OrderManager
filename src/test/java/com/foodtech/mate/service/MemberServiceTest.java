package com.foodtech.mate.service;

import com.foodtech.mate.domain.wrapper.account.Phone;
import com.foodtech.mate.dto.account.AccountDto;
import com.foodtech.mate.domain.entity.Account;
import com.foodtech.mate.domain.wrapper.account.UserId;
import com.foodtech.mate.repository.MemberQueryRepository;
import com.foodtech.mate.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.persistence.EntityNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@DisplayName("[유닛 테스트] - 회원 서비스")
public class MemberServiceTest {

    @MockBean
    private MemberRepository memberRepository;
    @MockBean
    private MemberQueryRepository memberQueryRepository;
    @MockBean
    private MemberService memberService;

    @Test
    @DisplayName("회원 가입 - 이메일 중복시 예외 발생")
    void existsEmail_signUp_throwException() {
        //given
        AccountDto accountDto = AccountDto.createAccountDto("test123", "testPassword123!", "김코딩", "010-1234-5678");
        Account account = accountDto.toEntity();
        given(memberQueryRepository.isUserIdExist(any())).willReturn(true);

        //when
        Throwable throwable = catchThrowable(() -> memberService.signUp(accountDto));

        //then
        assertThat(throwable).isInstanceOf(IllegalStateException.class);
        assertThat(throwable).hasMessage("! 이미 사용중인 아이디 입니다.");
    }

    @Test
    @DisplayName("회원 가입 - 올바른 회원정보로 가입시 회원생성")
    void properInfo_signUp_createMember() {
        //given
        AccountDto accountDto = AccountDto.createAccountDto("test123", "testPassword123!", "김코딩" , "010-1234-5678");
        Account account = accountDto.toEntity();
        given(memberRepository.save(any())).willReturn(account);

        // when
        Account createdAccount = memberRepository.save(account);

        //then
        assertThat(createdAccount).isNotNull();
        assertThat(createdAccount).isInstanceOf(Account.class);
    }

    @Test
    @DisplayName("아이디 찾기 - 등록된 연락처로 아이디 찾기 시도시 조회된 회원 아이디 반환")
    void registeredPhone_findUserId_returnUserId() {
        //given
        String phone = "010-1234-5678";
        AccountDto accountDto = AccountDto.createAccountDto("test123", "testPassword123!", "김코딩", "010-1234-5678");
        Account account = accountDto.toEntity();
        given(memberQueryRepository.findUserIdByPhone(Phone.of(phone))).willReturn(account.getUserId());

        //when
        UserId fountUserId = memberQueryRepository.findUserIdByPhone(Phone.of(phone));

        //then
        assertThat(fountUserId).isNotNull();
        assertThat(fountUserId.getUserId()).isEqualTo("test123");
    }

    @Test
    @DisplayName("아이디 찾기 - 등록되지 않은 연락처로 아이디찾기 시도시 예외 발생")
    void unregisteredPhone_findUserId_throwException() {
        //given
        String phone = "010-5678-1234";
        given(memberQueryRepository.findUserIdByPhone(Phone.of(phone))).willReturn(null);

        //when
        Throwable throwable = catchThrowable(() -> memberService.findUserId(phone));

        //then
        assertThat(throwable).isInstanceOf(EntityNotFoundException.class);
        assertThat(throwable).hasMessage("주문접수 앱 가입 정보가 없습니다.");
    }

    @Test
    @DisplayName("비밀번호 변경 - 등록된 아이디로 계정 찾기 시도시 조회된 회원 아이디 반환")
    void registeredUserId_findAccount_returnAccount() {
        //given
        String userId = "test123";
        AccountDto accountDto = AccountDto.createAccountDto("test123", "testPassword123!", "김코딩", "010-1234-5678");
        Account account = accountDto.toEntity();
        given(memberQueryRepository.findAccountByUserId(UserId.of(userId))).willReturn(account);

        //when
        Account fountAccount = memberQueryRepository.findAccountByUserId(UserId.of(userId));

        //then
        assertThat(fountAccount).isNotNull();
        assertThat(fountAccount).isInstanceOf(Account.class);

    }

    @Test
    @DisplayName("비밀번호 변경 - 등록되지 않은 아이디로 계정찾기 시도시 예외 발생")
    void unregisteredUserId_findAccount_throwException() {
        //given
        String userId = "김개발";
        given(memberQueryRepository.findUserIdByPhone(Phone.of(userId))).willReturn(null);

        //when
        Throwable throwable = catchThrowable(() -> memberService.findUserId(userId));

        //then
        assertThat(throwable).isInstanceOf(EntityNotFoundException.class);
        assertThat(throwable).hasMessage("주문접수 앱 가입 정보가 없습니다.");
    }

    @Test
    @DisplayName("비밀번호 변경 - 올바른 형식의 비밀번호를 입력받아 비밀번호 변경 시도시 변경된 비밀번호가 담긴 회원객체 반환")
    void inputNewPassword_requestChangePassword_returnAccountContainingTheChangedPassword() {
        //given
        String newPassword = "newTestPassword123!";
        AccountDto accountDto = AccountDto.createAccountDto("test123", "testPassword123!", "김코딩", "010-1234-5678");
        Account account = accountDto.toEntity();
        given(memberQueryRepository.findAccountByUserId(UserId.of(accountDto.getUserId()))).willReturn(account);

        // When
        account.encryptPassword(newPassword);
        memberService.changePassword(accountDto.getUserId(), accountDto.getPassword());

        Account changedAccount = memberQueryRepository.findAccountByUserId(UserId.of(accountDto.getUserId()));

        //then
        assertThat(changedAccount.passwordOf()).isEqualTo(newPassword);
    }
}
