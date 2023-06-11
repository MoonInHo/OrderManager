package com.order.manager.controller;

import com.order.manager.dto.FindUserIdResponseDto;
import com.order.manager.dto.ResponseMessageDto;
import com.order.manager.dto.ResponseStatusDto;
import com.order.manager.dto.VerificationCodeResponseDto;
import com.order.manager.dto.account.AccountDto;
import com.order.manager.dto.account.PasswordRequestDto;
import com.order.manager.dto.account.VerificationRequestDto;
import com.order.manager.enums.Verification;
import com.order.manager.enums.state.AccountResponseStatus;
import com.order.manager.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @GetMapping
    public String main() {
        return "mainPage";
    }

    @GetMapping("/sign-up")
    public String signUpPage() {
        return "signUpPage";
    }

    @GetMapping("/sign-in")
    public String signInPage() {
        return "signInPage";
    }

    @PostMapping("/sign-up")
    public ResponseEntity<ResponseStatusDto> signUp(@RequestBody AccountDto accountDto) {

        memberService.signUp(accountDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ResponseStatusDto(AccountResponseStatus.SIGN_UP_SUCCESS, "회원가입이 완료되었습니다"));
    }

    @PostMapping("/find-id")
    public ResponseEntity<?> requestVerificationCodeToFindUserId(@RequestBody VerificationRequestDto verificationRequestDto, HttpSession session) {

        String phone = verificationRequestDto.getPhone();

        if (isInvalidPhoneFormat(phone)) {
            return ResponseEntity
                    .badRequest()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new ResponseMessageDto("! 올바른 형식으로 입력해주세요."));
        }

        String verificationCode = memberService.generateVerification();

        //TODO 세션 사용 -> DB, TOKEN 등의 저장방법으로 변경
        session.setAttribute(Verification.PHONE.name(), phone);
        session.setAttribute(Verification.CODE.name(), verificationCode);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new VerificationCodeResponseDto(verificationCode));
    }

    @GetMapping("/find-id/verify")
    public ResponseEntity<?> findUserId(@RequestBody VerificationRequestDto verificationDto, HttpSession session) {

        String verificationCode = verificationDto.getVerificationCode();
        String phone = (String) session.getAttribute(Verification.PHONE.name());
        String storedVerificationCode = (String) session.getAttribute(Verification.CODE.name());

        if (isInvalidVerificationCode(verificationCode, storedVerificationCode)) {
            return ResponseEntity
                    .badRequest()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new ResponseMessageDto("인증 번호가 일치하지 않습니다."));
        }
        session.invalidate();

        String userId = memberService.findUserId(phone);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new FindUserIdResponseDto(userId));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> requestVerificationCodeToResetPassword(@RequestBody VerificationRequestDto verificationDto, HttpSession session) {

        String userId = verificationDto.getUserId();
        String name = verificationDto.getName();
        String phone = verificationDto.getPhone();

        if (isInvalidUserIdFormat(userId) || isInvalidNameFormat(name) || isInvalidPhoneFormat(phone)) {
            return ResponseEntity
                    .badRequest()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new ResponseMessageDto("! 올바른 형식으로 입력해주세요."));
        }
        String verificationCode = memberService.generateVerification();

        session.setAttribute(Verification.USERID.name(), userId);
        session.setAttribute(Verification.CODE.name(), verificationCode);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new VerificationCodeResponseDto(verificationCode));
    }

    @PostMapping("/reset-password/verify")
    public ResponseEntity<ResponseMessageDto> verifyPasswordResetCode(@RequestBody VerificationRequestDto verificationDto, HttpSession session) {

        String userId = (String) session.getAttribute(Verification.USERID.name());
        String storedVerificationCode = (String) session.getAttribute(Verification.CODE.name());
        String verificationCode = verificationDto.getVerificationCode();

        if (isInvalidVerificationCode(verificationCode, storedVerificationCode)) {
            return ResponseEntity
                    .badRequest()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new ResponseMessageDto("인증 번호가 일치하지 않습니다."));
        }

        memberService.findAccount(userId);

        session.setAttribute(Verification.USERID.name(), userId);
        session.setAttribute(Verification.VERIFICATION.name(), Verification.SUCCESS.name());
        session.removeAttribute(Verification.CODE.name());

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ResponseMessageDto("인증에 성공했습니다."));
    }

    @PatchMapping("/reset-password")
    public ResponseEntity<?> changePassword(@RequestBody PasswordRequestDto passwordDto, HttpSession session) {

        String userId = (String) session.getAttribute(Verification.USERID.name());
        String verification = (String) session.getAttribute(Verification.VERIFICATION.name());
        String password = passwordDto.getPassword();
        String confirmPassword = passwordDto.getConfirmPassword();

        ResponseEntity<ResponseMessageDto> APPLICATION_JSON = returnFailureMessage(verification, password, confirmPassword);
        if (APPLICATION_JSON != null) return APPLICATION_JSON;

        memberService.changePassword(userId, password);

        session.invalidate();

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ResponseMessageDto("비밀번호가 변경되었습니다."));
    }

    private ResponseEntity<ResponseMessageDto> returnFailureMessage(String verification, String password, String confirmPassword) {
        if (isVerificationFailure(verification)) {
            return ResponseEntity
                    .badRequest()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new ResponseMessageDto("! 본인인증 후 다시 시도해주세요."));
        }
        if (isInvalidPasswordFormat(password)) {
            return ResponseEntity
                    .badRequest()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new ResponseMessageDto("! 올바른 형식으로 입력해주세요."));
        }
        if (isPasswordConfirmationInvalid(password, confirmPassword)) {
            return ResponseEntity
                    .badRequest()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new ResponseMessageDto("! 비밀번호를 다시 확인해주세요."));
        }
        return null;
    }

    public boolean isInvalidUserIdFormat(String userId) {
        return !userId.matches("^[a-zA-Z0-9]{5,}$");
    }
    public boolean isInvalidPasswordFormat(String password) {
        return !password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%&*?])[A-Za-z\\d!@#$%&*?]{10,20}$");
    }
    public boolean isInvalidNameFormat(String name) {
        return !name.matches("^[가-힣]{2,5}$");
    }
    public boolean isInvalidPhoneFormat(String phone) {
        return !phone.matches("^010-(?:\\d{3}|\\d{4})-\\d{4}$");
    }

    private boolean isVerificationFailure(String verification) {
        return !verification.equals(Verification.SUCCESS.name());
    }

    private boolean isInvalidVerificationCode(String verificationCode, String storedVerificationCode) {
        return !storedVerificationCode.equals(verificationCode);
    }

    private boolean isPasswordConfirmationInvalid(String password, String confirmPassword) {
        return !password.equals(confirmPassword);
    }
}
