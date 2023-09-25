package com.mooninho.ordermanager.owner.application.dto.request;

import com.mooninho.ordermanager.owner.domain.entity.Owner;
import com.mooninho.ordermanager.owner.domain.vo.Name;
import com.mooninho.ordermanager.owner.domain.vo.Password;
import com.mooninho.ordermanager.owner.domain.vo.Phone;
import com.mooninho.ordermanager.owner.domain.vo.Username;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOwnerRequestDto {

    private String username;
    private String password;
    private String name;
    private String phone;

    public Owner toEntity() {
        return Owner.createOwner(
                Username.of(username),
                Password.of(password),
                Name.of(name),
                Phone.of(phone)
        );
    }
}
