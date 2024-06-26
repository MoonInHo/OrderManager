package com.mooninho.ordermanager.ownerapp.member.presentation;

import com.mooninho.ordermanager.ownerapp.member.application.dto.request.CreateMemberRequestDto;
import com.mooninho.ordermanager.ownerapp.member.application.dto.request.SignInRequestDto;
import com.mooninho.ordermanager.ownerapp.member.application.security.JwtUtil;
import com.mooninho.ordermanager.ownerapp.member.application.service.MemberService;
import com.mooninho.ordermanager.ownerapp.member.domain.entity.Member;
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
    public ResponseEntity<Void> signUp(@RequestBody CreateMemberRequestDto createOwnerRequestDto) {
        memberService.signUp(createOwnerRequestDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/sign-in")
    public ResponseEntity<String> signIn(@RequestBody SignInRequestDto signInRequestDto) {

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInRequestDto.username(),
                        signInRequestDto.password()
                )
        );
        Member owner = (Member) authenticate.getPrincipal();

        return ResponseEntity.ok().body(jwtUtil.generateToken(owner.username()));
    }

    //TODO 로그인 시도를 n번 시도시 토큰을 어떻게 관리할지 고민
    //TODO Redis 학습 후에 로그아웃 기능 구현 or Refresh token rotation 알아보기
}
