package com.mooninho.ordermanager.ownerapp.member.application.dto.request;

import com.mooninho.ordermanager.ownerapp.member.domain.entity.Member;
import com.mooninho.ordermanager.ownerapp.member.domain.vo.Name;
import com.mooninho.ordermanager.ownerapp.member.domain.vo.Password;
import com.mooninho.ordermanager.ownerapp.member.domain.vo.Phone;
import com.mooninho.ordermanager.ownerapp.member.domain.vo.Username;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateMemberRequestDto {

    private String username;
    private String password;
    private String name;
    private String phone;

    public Member toEntity() {
        return Member.createMember(
                Username.of(username),
                Password.of(password),
                Name.of(name),
                Phone.of(phone)
        );
    }
}
