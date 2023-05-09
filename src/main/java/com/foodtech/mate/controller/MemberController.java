package com.foodtech.mate.controller;

import com.foodtech.mate.controller.verifier.Verifier;
import com.foodtech.mate.domain.dto.account.AccountDto;
import com.foodtech.mate.domain.dto.account.ChangePasswordDto;
import com.foodtech.mate.domain.dto.account.VerificationDto;
import com.foodtech.mate.domain.entity.Account;
import com.foodtech.mate.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.foodtech.mate.controller.verifier.Verifier.verifyCode;
import static com.foodtech.mate.controller.verifier.Verifier.verifyPassword;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final PasswordEncoder passwordEncoder;
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

        Account account = AccountDto.createAccount(accountDto);
        account.encryptPassword(passwordEncoder.encode(account.passwordOf()));
        Long userId = memberService.signUp(account);

        return ResponseEntity.ok(userId);
    }

    @PostMapping("/generate-verificationCode")
    public ResponseEntity<String> sendVerificationDtoCodeToFindUserId(@RequestBody VerificationDto verificationDto) {

        String verificationCode = Verifier.generateUserIdVerificationCode(verificationDto, verificationMap);
        verificationMap.put(verificationDto.getPhone(), verificationCode);

        return ResponseEntity.ok("인증번호 : " + verificationCode);
    }

    @PostMapping("/find-userId")
    public ResponseEntity<String> findUserId(@RequestBody VerificationDto verificationDto) {

        verifyCode(verificationDto, verificationMap);
        String userId = memberService.findUserId(verificationDto.getPhone());

        return ResponseEntity.ok("조회하신 아이디는 '" + userId + "' 입니다");
    }
    
    @PostMapping("/create-verification-password")
    public ResponseEntity<String> sendVerificationCodeToFindPassword(@RequestBody VerificationDto verificationDto) {

        String verificationCode = Verifier.generatePasswordVerificationCode(verificationDto, verificationMap);

        verificationMap.put(verificationDto.getPhone(), verificationCode);

        return ResponseEntity.ok("인증번호 : " + verificationCode);
    }

    @PostMapping("/verification-password")
    public ResponseEntity<String> verificationRequestChangePassword(@RequestBody VerificationDto verificationDto) {

        String userId = verificationDto.getUserId();
        verifyCode(verificationDto, verificationMap);
        
        verificationMap.put("userId", userId);
        verificationMap.put("verification", "success");

        return ResponseEntity.ok("인증에 성공하였습니다.");
    }

    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordDto changePasswordDto) {

        String userId = verificationMap.get("userId");
        String password = verifyPassword(changePasswordDto, verificationMap);
        Account account = memberService.findAccount(userId);

        if (passwordEncoder.matches(password, account.passwordOf())) {
            return ResponseEntity.badRequest().body("! 기존 비밀번호로 변경하실 수 없습니다.");
        }
        account.encryptPassword(passwordEncoder.encode(password));
        memberService.changePassword(account);

        verificationMap.clear();

        return ResponseEntity.ok("비밀번호가 변경되었습니다.");
    }
}
