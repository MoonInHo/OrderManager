package com.foodtech.mate.controller;

import com.foodtech.mate.domain.dto.account.AccountDto;
import com.foodtech.mate.domain.dto.account.PasswordRequestDto;
import com.foodtech.mate.domain.dto.account.VerificationRequestDto;
import com.foodtech.mate.enums.Verification;
import com.foodtech.mate.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;
    private final Map<String, String> verificationMap = new ConcurrentHashMap<>();

    @GetMapping("/")
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
    public ResponseEntity<Long> signUp(@RequestBody AccountDto accountDto) {

        Long userCode = memberService.signUp(accountDto);

        return ResponseEntity.ok(userCode);
    }

    @PostMapping("/userid-find/request")
    public ResponseEntity<String> requestVerificationCodeToFindUserId(@RequestBody VerificationRequestDto verificationDto, HttpSession session) {

        String phone = verificationDto.getPhone();
        if (isInvalidPhoneFormat(phone)) {
            return ResponseEntity.badRequest().body("! 올바른 형식으로 입력해주세요.");
        }
        String verificationCode = memberService.generateVerification();

        session.setAttribute(Verification.PHONE.name(), phone);
        session.setAttribute(Verification.CODE.name(), verificationCode);

        return ResponseEntity.ok("인증번호 : " + verificationCode);
    }

    @PostMapping("/userId-find/verify")
    public ResponseEntity<String> findUserId(@RequestBody VerificationRequestDto verificationDto, HttpSession session) {

        String verificationCode = verificationDto.getVerificationCode();
        String phone = (String) session.getAttribute(Verification.PHONE.name());
        String storedVerificationCode = (String) session.getAttribute(Verification.CODE.name());

        session.invalidate();

        if (isInvalidVerificationCode(verificationCode, storedVerificationCode)) {
            return ResponseEntity.badRequest().body("! 인증 번호가 일치하지 않습니다.");
        }
        String userId = memberService.findUserId(phone);

        return ResponseEntity.ok("조회하신 아이디는 '" + userId + "' 입니다");
    }

    @PostMapping("/password-reset/request")
    public ResponseEntity<String> requestVerificationCodeToResetPassword(@RequestBody VerificationRequestDto verificationDto, HttpSession session) {

        String userId = verificationDto.getUserId();
        String name = verificationDto.getName();
        String phone = verificationDto.getPhone();

        if (isInvalidUserIdFormat(userId) || isInvalidNameFormat(name) || isInvalidPhoneFormat(phone)) {
            return ResponseEntity.badRequest().body("! 올바른 형식으로 입력해주세요.");
        }
        String verificationCode = memberService.generateVerification();

        session.setAttribute(Verification.USERID.name(), userId);
        session.setAttribute(Verification.CODE.name(), verificationCode);

        return ResponseEntity.ok("인증번호 : " + verificationCode);
    }

    @PostMapping("/password-reset/verify")
    public ResponseEntity<String> verifyPasswordResetCode(@RequestBody VerificationRequestDto verificationDto, HttpSession session) {

        String userId = (String) session.getAttribute(Verification.USERID.name());
        String storedVerificationCode = (String) session.getAttribute(Verification.CODE.name());
        String verificationCode = verificationDto.getVerificationCode();

        memberService.findAccount(userId);

        if (isInvalidVerificationCode(verificationCode, storedVerificationCode)) {
            return ResponseEntity.badRequest().body("! 인증 번호가 일치하지 않습니다.");
        }

        session.setAttribute(Verification.USERID.name(), userId);
        session.setAttribute(Verification.VERIFICATION.name(), Verification.SUCCESS.name());

        return ResponseEntity.ok("인증에 성공하였습니다.");
    }

    @PostMapping("/password-reset")
    public ResponseEntity<String> changePassword(@RequestBody PasswordRequestDto passwordDto, HttpSession session) {

        String userId = (String) session.getAttribute(Verification.USERID.name());
        String verification = (String) session.getAttribute(Verification.VERIFICATION.name());

        session.invalidate();

        String password = passwordDto.getPassword();
        String confirmPassword = passwordDto.getConfirmPassword();

        if (isVerificationFailure(verification)) {
            return ResponseEntity.badRequest().body("! 본인인증 후 다시 시도해주세요.");
        }

        if (isInvalidPasswordFormat(password)) {
            return ResponseEntity.badRequest().body("! 올바른 형식으로 입력해주세요.");
        }

        if (isPasswordConfirmationInvalid(password, confirmPassword)) {
            return ResponseEntity.badRequest().body("! 비밀번호를 다시 확인해주세요.");
        }

        memberService.changePassword(userId, password);

        return ResponseEntity.ok("비밀번호가 변경되었습니다.");
    }

    //TODO 회원정보 변경 기능 추가
    private boolean isInvalidUserIdFormat(String userId) {
        return !userId.matches("^[a-zA-Z0-9]{5,}$");
    }

    private boolean isInvalidPasswordFormat(String password) {
        return !password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%&*?])[A-Za-z\\d!@#$%&*?]{10,20}$");
    }

    private boolean isInvalidNameFormat(String name) {
        return !name.matches("^[가-힣]{2,5}$");
    }

    private boolean isInvalidPhoneFormat(String phone) {
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
