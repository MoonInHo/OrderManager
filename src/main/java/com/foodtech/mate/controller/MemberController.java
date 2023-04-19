package com.foodtech.mate.controller;

import com.foodtech.mate.domain.dto.AccountDto;
import com.foodtech.mate.domain.entity.Account;
import com.foodtech.mate.exception.InvalidPasswordException;
import com.foodtech.mate.exception.InvalidUserIdException;
import com.foodtech.mate.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final PasswordEncoder passwordEncoder;
    private final MemberService memberService;

    @PostMapping("/sign_up")
    public ResponseEntity<Long> signUp(@RequestBody AccountDto accountDto) {

        if (!accountDto.getUsername().matches("^[a-zA-Z0-9]{5,}$")) {
            throw new InvalidUserIdException("! 올바른 형식으로 입력해주세요.");
        }

        if (!accountDto.getPassword().matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%&*?])[A-Za-z\\d!@#$%&*?]{10,20}$")) {
            throw new InvalidPasswordException("! 올바른 형식으로 입력해주세요.");
        }

        Account account = Account.createMember(accountDto);
        account.passwordEncoding(passwordEncoder.encode(account.passwordOf()));
        Long userId = memberService.signUp(account);

        return ResponseEntity.ok(userId);
    }
}
