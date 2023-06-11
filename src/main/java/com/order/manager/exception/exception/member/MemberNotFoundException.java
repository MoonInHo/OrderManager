package com.order.manager.exception.exception.member;

public class MemberNotFoundException extends RuntimeException {

    public MemberNotFoundException() {
        super("주문관리 앱 가입 정보가 없습니다.");
    }
}
