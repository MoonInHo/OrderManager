package com.foodtech.mate.domain.entity;

import com.foodtech.mate.domain.dto.AccountDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "! 공백을 사용할 수 없습니다.")
    @Column(unique = true)
    private String userId;
    @NotBlank(message = "! 공백을 사용할 수 없습니다.")
    private String password;

    private Account(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public void passwordEncoding(String password) {
        this.password = password;
    }

    public static Account createMember(AccountDto accountDto) {
        return new Account(accountDto.getUserId(), accountDto.getPassword());
    }
}
