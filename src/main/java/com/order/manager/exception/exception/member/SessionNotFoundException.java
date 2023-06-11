package com.order.manager.exception.exception.member;

public class SessionNotFoundException extends RuntimeException {

    public SessionNotFoundException() {
        super("로그인 정보가 존재하지 않습니다.");
    }
}

