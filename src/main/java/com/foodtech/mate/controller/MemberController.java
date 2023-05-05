package com.foodtech.mate.controller;

import com.foodtech.mate.domain.dto.AccountDto;
import com.foodtech.mate.domain.dto.CertificationDto;
import com.foodtech.mate.domain.entity.Account;
import com.foodtech.mate.domain.wrapper.Phone;
import com.foodtech.mate.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final PasswordEncoder passwordEncoder;
    private final MemberService memberService;
    private final Map<String, String> certificationMap = new ConcurrentHashMap<>();

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

    @PostMapping("/create-certification")
    public ResponseEntity<String> createCertification(@RequestBody CertificationDto certificationDto) {

        Phone.of(certificationDto.getPhone());
        String phone = certificationDto.getPhone();

        if (certificationMap.size() != 0) {
            certificationMap.remove(phone);
        }
        String certificationCode = memberService.createCertificationCode();
        certificationMap.put(phone, certificationCode);

        return ResponseEntity.ok("인증번호 : " + certificationCode);
    }

    @PostMapping("/certification")
    public ResponseEntity<String> certification(@RequestBody CertificationDto certificationDto) {

        String phone = certificationDto.getPhone();

        String savedCode = certificationMap.get(phone);
        if (savedCode != null && savedCode.equals(certificationDto.getCertificationCode())) {
            certificationMap.remove(phone);
            String username = memberService.findUsername(phone);

            return ResponseEntity.ok("조회하신 아이디는 '" + username + "' 입니다");
        }
        return ResponseEntity.badRequest().body("인증번호가 일치하지 않습니다.");
    }
}
