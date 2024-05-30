package com.mooninho.ordermanager.ownerapp.member.domain.vo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[유닛 테스트] - 역할 도메인")
class RoleTest {

    @Test
    @DisplayName("역할 입력 - 올바른 역할 입력시 역할 객체 생성")
    void properRoleFormat_createRoleObject() {
        //given & when
        Role role = Role.of("ROLE_USER");

        //then
        assertThat(role).isInstanceOf(Role.class);
        assertThat(role.getRole()).isEqualTo("ROLE_USER");
    }
}