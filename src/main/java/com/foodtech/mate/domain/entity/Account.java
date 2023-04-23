package com.foodtech.mate.domain.entity;

import com.foodtech.mate.domain.dto.AccountDto;
import com.foodtech.mate.domain.wrapper.Password;
import com.foodtech.mate.domain.wrapper.Username;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    private Account(Username username, Password password) {
        this.username = username;
        this.password = password;
    }

    public void encryptPassword(String password) {
        this.password = Password.of(password);
    }

    public static Account createMember(AccountDto accountDto) {
        return new Account(
                Username.of(accountDto.getUsername()),
                Password.of(accountDto.getPassword())
        );
    }

    public String usernameOf() {
        return this.username.getUsername();
    }

    public String passwordOf() {
        return this.password.getPassword();
    }
}
