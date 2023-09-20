package com.mooninho.ordermanager.member.domain.entity;

import com.mooninho.ordermanager.member.domain.vo.*;
import com.mooninho.ordermanager.store.domain.entity.Store;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Embedded
    @Column(nullable = false, unique = true)
    private UserId userId;

    @Embedded
    @Column(nullable = false)
    private Password password;

    @Embedded
    @Column(nullable = false)
    private Name name;

    @Embedded
    @Column(nullable = false, unique = true)
    private Phone phone;

    @Embedded
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Store> stores = new ArrayList<>();

    private Member(Long id) {
        this.id = id;
    }

    private Member(UserId userId, Password password, Name name, Phone phone) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.role = Role.of("ROLE_USER");
    }

    public static Member createMember(UserId userId, Password password, Name name, Phone phone) {
        return new Member(userId, password, name, phone);
    }

    public static Member createKeyObject(Long memberId) {
        return new Member(memberId);
    }

    public void passwordEncrypt(PasswordEncoder passwordEncoder) {
        this.password = Password.encodedPassword(passwordEncoder.encode(this.password.password()));
    }

    public List<GrantedAuthority> createRole() {
        return Collections.singletonList(role.createRole());
    }

    public String userId() {
        return userId.userId();
    }

    public String password() {
        return password.password();
    }
}
