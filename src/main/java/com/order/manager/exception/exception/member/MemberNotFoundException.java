package com.order.manager.exception.exception.member;

public class MemberNotFoundException extends RuntimeException {

    public MemberNotFoundException() {
        super("가입 정보가 존재하지 않습니다.");
    }
}
