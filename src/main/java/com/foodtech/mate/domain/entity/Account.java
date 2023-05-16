package com.foodtech.mate.domain.entity;

import com.foodtech.mate.domain.wrapper.account.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private UserId userId;
    @Embedded
    private Password password;
    @Embedded
    private Name name;
    @Embedded
    private Phone phone;
    @Embedded
    private Role role;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private List<Store> store = new ArrayList<>();

    private Account(UserId userId, Password password,Name name, Phone phone) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.role = Role.of("ROLE_USER");
    }

    public void encryptPassword(String password) {
        this.password = Password.encodedPassword(password);
    }

    public static Account createMember(UserId userId, Password password, Name name, Phone phone) {
        return new Account(userId, password, name, phone);
    }

    public String userIdOf() {
        return userId.getUserId();
    }

    public String passwordOf() {
        return password.getPassword();
    }

    public List<GrantedAuthority> createRole() {
        return Collections.singletonList(new SimpleGrantedAuthority(role.getRole()));
    }
}
