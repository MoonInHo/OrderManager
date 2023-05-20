package com.foodtech.mate.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodtech.mate.controller.verifier.Verifier;
import com.foodtech.mate.domain.dto.account.AccountDto;
import com.foodtech.mate.domain.dto.account.VerificationDto;
import com.foodtech.mate.domain.entity.Account;
import com.foodtech.mate.security.configs.SecurityConfig;
import com.foodtech.mate.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("[유닛 테스트] - 회원 컨트롤러")
@WebMvcTest(MemberController.class)
@Import(SecurityConfig.class)
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private MemberController memberController;

    @MockBean
    private MemberService memberService;


    @Test
    @DisplayName("접근 권한 - 익명 사용자가 회원가입 자원 접근시 200ok 반환")
    @WithAnonymousUser
    void anonymousUser_accessSignInPage_returnHttpStatusOk() throws Exception {
        mockMvc.perform(get("/sign-in"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("접근 권한 - 익명 사용자가 로그인 자원 접근시 200ok 반환")
    @WithAnonymousUser
    void anonymousUser_accessSignUpPage_returnHttpStatusOk() throws Exception {
        mockMvc.perform(get("/sign-up"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("접근 권한 - 익명 사용자가 메인 페이지 접근시 302 상태코드 반환 후 로그인 페이지로 리다이렉트")
    @WithAnonymousUser
    void anonymousUser_accessMainPage_returnStatusFoundAndRedirectSignInPage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("http://localhost/sign-in"));
    }

    @Test
    @DisplayName("접근 권한 - 회원이 로그인 페이지 접근시 403 상태코드 반환")
    @WithMockUser(authorities = "USER")
    void member_accessSignInPage_returnStatusForbidden() throws Exception {
        mockMvc.perform(get("/sign-in"))
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
    @WithMockUser(username = "test123", roles = "USER")
    void member_accessMainPage_returnStatusOk() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    //TODO 로그인 기능 테스트코드 작성 & 스프링 시큐리티 설정 확인
    @Test
    @DisplayName("회원 로그인 - 올바른 회원정보로 로그인 시도시 200ok 반환")
    @WithAnonymousUser
    void properInfo_signIn_returnStatusOk() throws Exception {
        mockMvc.perform(post("/sign-in-proc")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("username", "test123")
                        .param("password", "testPassword123!"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    @DisplayName("회원 가입 - 올바른 사용자 정보 입력시 회원정보 저장")
    @WithAnonymousUser
    void properAccountInfo_signUp_createMember() throws Exception {
        //given
        AccountDto accountDto = AccountDto.createAccountDto("test123", "testPassword123!", "김코딩", "010-1234-5678");
        String json = new ObjectMapper().writeValueAsString(accountDto);

        //when & then
        mockMvc.perform(post("/sign-up")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("로그아웃 - 회원 로그아웃시 세션, 쿠키정보 만료")
    @WithMockUser(roles = "USER")
    void member_signOut_expirationSessionAndCookies() throws Exception {
        mockMvc.perform(post("/sign-out"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/sign-in"))
                .andExpect(cookie().value("JSESSIONID", (String) null));
    }

    @Test
    @DisplayName("비밀번호 인코딩 - 사용자가 입력한 비밀번호를 인코딩 후 암호화된 비밀번호 반환")
    void password_encoding_returnEncodedPassword() {
        //given
        AccountDto accountDto = AccountDto.createAccountDto("test123", "testPassword123!", "김코딩", "010-1234-5678");
        Account account = AccountDto.toEntity(accountDto);

        //when
        account.encryptPassword(passwordEncoder.encode(account.passwordOf()));

        //then
        assertThat(passwordEncoder.matches(accountDto.getPassword(), account.passwordOf()));
    }


    //TODO responseBody 가 빈 문자열인 이유
    @Test
    @DisplayName("아이디 찾기 - 연락처를 입력 후 인증 요청시 인증번호 반환")
    @WithAnonymousUser
    void inputPhone_requestVerification_returnVerificationCode() throws Exception {
        // given
        String phone = "010-1234-5678";
        VerificationDto verificationDto = VerificationDto.createVerificationDto(null, null, phone, null);
        Map<String, String> verificationMap = new ConcurrentHashMap<>();
        String verificationCode = Verifier.generateUserIdVerificationCode(verificationDto, verificationMap);

        verificationMap.put(phone, verificationCode);

        String expectResponseBody = "인증번호 : " + verificationCode;
        String json = new ObjectMapper().writeValueAsString(verificationDto);

        //when
        MvcResult result =
                mockMvc.perform(post("/generate-verificationCode")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andReturn();

        // then
        String responseBody = result.getResponse().getContentAsString();

        assertThat(expectResponseBody).isEqualTo(responseBody);
        assertThat(verificationCode).isEqualTo(verificationMap.get("010-1234-5678"));
    }

    @Test
    @DisplayName("아이디 찾기 - 올바르지 않은 인증번호로 인증 시도시 예외 반환")
    void InvalidVerificationCode_requestVerification_throwException() {

    }

    @Test
    @DisplayName("아이디 찾기 - 인증 성공 후 회원 아이디 찾기 요청시 조회된 아이디 반환")
    void successVerification_findUsername_returnFoundUsername() throws Exception {

    }

    @Test
    @DisplayName("비밀번호 찾기 - 회원정보 입력 후 인증 요청시 인증번호 반환")
    void inputMemberInfo_requestVerification_returnVerificationCode() {
    }

    @Test
    @DisplayName("비밀번호 찾기 - 올바른 인증번호로 인증시도시 성공 메세지 반환 후 인증자 정보 저장")
    void inputProperVerificationCode_requestVerification_returnStatusOkAndSaveRequest() {

    }

    @Test
    @DisplayName("비밀번호 변경 - 변경할 비밀번호를 입력받아 검증 성공시 암호화 후 비밀번호를 저장한 뒤 성공 메세지 반환")
    void inputNewPassword_successVerificationPerformPasswordEncoding_savePasswordAndReturnStatusOk() {

    }
}

