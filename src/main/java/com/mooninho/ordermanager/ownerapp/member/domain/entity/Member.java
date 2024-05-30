package com.mooninho.ordermanager.ownerapp.member.domain.entity;

import com.mooninho.ordermanager.ownerapp.member.domain.vo.*;
import com.mooninho.ordermanager.ownerapp.store.domain.entity.Store;
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
    @Column(name = "owner_id")
    private Long id;

    @Embedded
    @Column(nullable = false, unique = true)
    private Username username;

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

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Store> stores = new ArrayList<>();

    private Member(Long id) {
        this.id = id;
    }

    private Member(Username username, Password password, Name name, Phone phone) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.role = Role.of("ROLE_USER");
    }

    public static Member createMember(Username username, Password password, Name name, Phone phone) {
        return new Member(username, password, name, phone);
    }

    public static Member createKeyObject(Long ownerId) {
        return new Member(ownerId);
    }

    public void passwordEncrypt(PasswordEncoder passwordEncoder) { // TODO PasswordEncoder 의존성 끊을 방법 고민
        this.password = password.encodedPassword(passwordEncoder);
    }

    public List<GrantedAuthority> createRole() {
        return Collections.singletonList(role.createRole()); // TODO  singletonList 사용 이유 학습
    }

    public String username() {
        return username.username();
    }

    public String password() {
        return password.password();
    }
}
