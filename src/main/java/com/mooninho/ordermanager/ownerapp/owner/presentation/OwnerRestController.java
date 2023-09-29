package com.mooninho.ordermanager.ownerapp.owner.presentation;

import com.mooninho.ordermanager.ownerapp.owner.application.dto.request.CreateOwnerRequestDto;
import com.mooninho.ordermanager.ownerapp.owner.application.dto.request.SignInRequestDto;
import com.mooninho.ordermanager.ownerapp.owner.application.security.JwtUtil;
import com.mooninho.ordermanager.ownerapp.owner.application.service.OwnerService;
import com.mooninho.ordermanager.ownerapp.owner.domain.entity.Owner;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/owners")
@RequiredArgsConstructor
public class OwnerRestController {

    private final OwnerService ownerService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @GetMapping("/test")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok().body("성공 페이지");
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Void> signUp(@RequestBody CreateOwnerRequestDto createOwnerRequestDto) {
        ownerService.signUp(createOwnerRequestDto);
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
        Owner owner = (Owner) authenticate.getPrincipal();

        return ResponseEntity.ok().body(jwtUtil.generateToken(owner.username()));
    }

    //TODO 로그인 시도를 n번 시도시 토큰을 어떻게 관리할지 고민
    //TODO Redis 학습 후에 로그아웃 기능 구현
}
