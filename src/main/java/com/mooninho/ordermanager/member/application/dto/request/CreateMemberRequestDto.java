package com.mooninho.ordermanager.member.application.dto.request;

import com.mooninho.ordermanager.member.domain.vo.Name;
import com.mooninho.ordermanager.member.domain.vo.Password;
import com.mooninho.ordermanager.member.domain.vo.Phone;
import com.mooninho.ordermanager.member.domain.vo.UserId;
import com.mooninho.ordermanager.member.domain.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateMemberRequestDto {

    private String userId;
    private String password;
    private String name;
    private String phone;

    public Member toEntity() {
        return Member.createMember(
                UserId.of(userId),
                Password.of(password),
                Name.of(name),
                Phone.of(phone)
        );
    }
}
