package com.foodtech.mate.domain.entity;

import com.foodtech.mate.domain.dto.AccountDto;
import com.foodtech.mate.domain.wrapper.Password;
import com.foodtech.mate.domain.wrapper.Role;
import com.foodtech.mate.domain.wrapper.Username;
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
    private Username username;
    @Embedded
    private Password password;
    @Embedded
    private Role role;

    private Account(Username username, Password password) {
        this.username = username;
        this.password = password;
        this.role = Role.of("ROLE_USER");
    }

    public void encryptPassword(String password) {
        this.password = Password.encodedPassword(password);
    }

    public static Account createMember(AccountDto accountDto) {
        return new Account(
                Username.of(accountDto.getUsername()),
                Password.of(accountDto.getPassword())
        );
    }

    public String usernameOf() {
        return username.getUsername();
    }

    public String passwordOf() {
        return password.getPassword();
    }

    public List<GrantedAuthority> createDefaultRoles(Role ...roles) {
        List<SimpleGrantedAuthority> defaultRoles = List.of(role.createRole());
        return Arrays.stream(roles)
                .map(Role::createRole)
                .collect(Collectors.toList());
    }
}
