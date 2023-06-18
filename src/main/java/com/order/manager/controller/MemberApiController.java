package com.order.manager.controller;

import com.order.manager.domain.wrapper.account.Phone;
import com.order.manager.domain.wrapper.account.VerificationCode;
import com.order.manager.dto.FindUserIdResponseDto;
import com.order.manager.dto.ResponseMessageDto;
import com.order.manager.dto.ResponseStatusDto;
import com.order.manager.dto.VerificationCodeResponseDto;
import com.order.manager.dto.account.AccountDto;
import com.order.manager.dto.account.PasswordRequestDto;
import com.order.manager.dto.account.VerificationRequestDto;
import com.order.manager.enums.state.AccountResponseStatus;
import com.order.manager.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> requestVerificationCodeToFindUserId(@RequestBody VerificationRequestDto verificationRequestDto) {

        String verificationCode = memberService.generateVerification();

        memberService.saveVerificationToUserId(verificationRequestDto, verificationCode);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new VerificationCodeResponseDto(verificationCode));
    }

    @GetMapping("/find-id/verify")
    public ResponseEntity<?> findUserId(@RequestBody VerificationRequestDto verificationRequestDto) {

        Phone phone = Phone.of(verificationRequestDto.getPhone());
        VerificationCode verificationCode = VerificationCode.of(verificationRequestDto.getVerificationCode());

        memberService.validateVerificationCodeUserId(phone, verificationCode);
        memberService.deleteVerificationInfo(phone);

        String userId = memberService.findUserIdByPhone(phone);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new FindUserIdResponseDto(userId));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> requestVerificationCodeToResetPassword(@RequestBody VerificationRequestDto verificationRequestDto) {

        String verificationCode = memberService.generateVerification();

        memberService.saveVerificationToPassword(verificationRequestDto, verificationCode);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new VerificationCodeResponseDto(verificationCode));
    }

    @PostMapping("/reset-password/verify")
    public ResponseEntity<ResponseMessageDto> verifyPasswordResetCode(@RequestBody VerificationRequestDto verificationRequestDto) {

        memberService.validateVerificationCodeToPassword(verificationRequestDto);
        memberService.findAccount(verificationRequestDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ResponseMessageDto("인증에 성공했습니다."));
    }

    @PatchMapping("/reset-password")
    public ResponseEntity<?> changePassword(@RequestBody PasswordRequestDto passwordRequestDto) {

        Phone phone = Phone.of(passwordRequestDto.getPhone());

        memberService.checkVerification(phone);
        memberService.deleteVerificationInfo(phone);

        memberService.changePassword(passwordRequestDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ResponseMessageDto("비밀번호가 변경되었습니다."));
    }
}
