package com.mooninho.ordermanager.member.presentation;

import com.mooninho.ordermanager.member.application.dto.CreateMemberRequestDto;
import com.mooninho.ordermanager.member.application.dto.SignInRequestDto;
import com.mooninho.ordermanager.member.application.security.JwtUtil;
import com.mooninho.ordermanager.member.application.service.MemberService;
import com.mooninho.ordermanager.member.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberRestController {

    private final MemberService memberService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @GetMapping("/test")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok().body("성공 페이지");
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Void> signUp(@RequestBody CreateMemberRequestDto createMemberRequestDto) {
        memberService.signUp(createMemberRequestDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/sign-in")
    public ResponseEntity<String> signIn(@RequestBody SignInRequestDto signInRequestDto) {

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInRequestDto.userId(),
                        signInRequestDto.password()
                )
        );

        Member member = (Member) authenticate.getPrincipal();

        return ResponseEntity.ok().body(jwtUtil.generateToken(member.userId()));
    }

    //TODO Redis 학습 후에 로그아웃 기능 구현
}
