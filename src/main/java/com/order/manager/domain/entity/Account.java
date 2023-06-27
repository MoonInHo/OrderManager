package com.order.manager.domain.entity;

import com.order.manager.domain.wrapper.account.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
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

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Store> store = new ArrayList<>();

    private Account(UserId userId, Password password, Name name, Phone phone) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.role = Role.of("ROLE_USER");
    }

    public static Account createMember(UserId userId, Password password, Name name, Phone phone) {
        return new Account(userId, password, name, phone);
    }

    public Long isUserKey() {
        return id;
    }

    public String isUserId() {
        return userId.isUserId();
    }

    public String isPassword() {
        return password.isPassword();
    }

    public List<GrantedAuthority> createRole() {
        return Collections.singletonList(role.createRole());
    }
}
