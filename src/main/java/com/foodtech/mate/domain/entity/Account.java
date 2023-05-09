package com.foodtech.mate.domain.entity;

import com.foodtech.mate.domain.wrapper.account.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Store> store;

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

    public String nameOf() {
        return name.getName();
    }

    public String phoneOf() {
        return phone.getPhone();
    }

    public List<GrantedAuthority> createDefaultRoles(Role ...roles) {
        List<SimpleGrantedAuthority> defaultRoles = List.of(role.createRole());
        return Arrays.stream(roles)
                .map(Role::createRole)
                .collect(Collectors.toList());
    }
}
