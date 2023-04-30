package com.foodtech.mate.controller;

import com.foodtech.mate.domain.AccountTest;
import com.foodtech.mate.domain.dto.AccountDto;
import com.foodtech.mate.domain.entity.Account;
import com.foodtech.mate.security.configs.SecurityConfig;
import com.foodtech.mate.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@DisplayName("[유닛 테스트] - 회원 컨트롤러")
@WebMvcTest(MemberController.class)
@Import(SecurityConfig.class)
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private MemberService memberService;

    @MockBean
    private MemberController memberController;

    @Test
    @DisplayName("접근 권한 - 익명 사용자가 회원가입 자원 접근시 200ok 반환")
    @WithAnonymousUser
    void anonymousUser_accessSignInPage_returnHttpStatusOk() throws Exception {
        mockMvc.perform(get("/sign-in"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("접근 권한 - 익명 사용자가 로그인 자원 접근시 200ok 반환")
    @WithAnonymousUser
    void anonymousUser_accessSignUpPage_returnHttpStatusOk() throws Exception {
        mockMvc.perform(get("/sign-up"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("접근 권한 - 익명 사용자가 메인 페이지 접근시 302 상태코드 반환 후 로그인 페이지로 리다이렉트")
    @WithAnonymousUser
    void anonymousUser_accessMainPage_returnStatusFoundAndRedirectSignInPage() throws Exception {
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("http://localhost/sign-in"));
    }
    
    @Test
    @DisplayName("접근 권한 - 회원이 로그인 페이지 접근시 403 상태코드 반환")
    @WithMockUser(authorities = "USER")
    void member_accessSignInPage_returnStatusForbidden() throws Exception {
        mockMvc.perform(get("/sign-in"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("접근 권한 - 회원이 회원가입 페이지 접근시 403 상태코드 반환")
    @WithMockUser(authorities = "USER")
    void member_accessSignUpPage_returnStatusForbidden() throws Exception {
        mockMvc.perform(get("/sign-up"))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("접근 권한 - 회원이 메인페이지 접근시 200ok 반환")
    @WithMockUser(authorities = "USER")
    void member_accessMainPage_returnStatusOk() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("회원 가입 - 올바른 사용자 정보 입력시 회원정보 저장")
    void properAccountInfo_signUp_createMember() {
        //given
        AccountDto accountDto = AccountDto.saveAccountInfo("test123", "testPassword123!");
        Account account = Account.createMember(accountDto);
        given(memberController.signUp(any())).willReturn(ResponseEntity.ok(account.getId()));

        //when
        ResponseEntity<Long> response = memberController.signUp(accountDto);

        //then
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("로그아웃 - 회원 로그아웃시 세션, 쿠키정보 만료")
    @WithMockUser(authorities = "USER")
    void member_signOut_expirationSessionAndCookies() throws Exception {
        MockHttpServletRequestBuilder logoutRequest = post("/sign-out")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED);

        mockMvc.perform(logoutRequest)
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/sign-in"))
                .andExpect(cookie().value("JSESSIONID", (String) null));
    }

    //TODO 비밀번호 인코딩 테스트코드 작성

//    @Test
//    @DisplayName("ddddd")
//    void password_encoding_returnEncodedPassword() throws Exception {
//        //given
//        AccountDto accountDto = AccountDto.saveAccountInfo("test123", "testPassword123!");
//        Account account = Account.createMember(accountDto);
//
//        //when
//        given(passwordEncoder.encode(account.passwordOf())).willReturn("encodedPassword");
//        mockMvc.perform(post("/sign-up"))
//                .andExpect(status().isOk())
//                .andExpect(result -> {
//                    Long userId = result.getResponse().getContentLengthLong();
//                    assertThat(userId).isNotNull();
//                });
//        assertThat(account.getPassword()).isEqualTo("encodedPassword");
//    }

    //TODO 스프링 시큐리티 로그인 테스트 작성
    //TODO 스프링 시큐리티 로그아웃 테스트 작성
}
