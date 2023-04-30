package com.foodtech.mate.controller;

import com.foodtech.mate.domain.dto.AccountDto;
import com.foodtech.mate.domain.entity.Account;
import com.foodtech.mate.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final PasswordEncoder passwordEncoder;
    private final MemberService memberService;

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

        Account account = Account.createMember(accountDto);
        account.encryptPassword(passwordEncoder.encode(account.passwordOf()));
        Long userId = memberService.signUp(account);

        return ResponseEntity.ok(userId);
    }

    @PostMapping("/sign-in")
    public void signIn(@RequestBody AccountDto accountDto) {
    }

    @PostMapping("/sign-out")
    public void signOut() {
    }
}
