package com.foodtech.mate.exception.exception.member;

public class MemberNotFoundException extends RuntimeException {

    public MemberNotFoundException() {
        super("주문접수 앱 가입 정보가 없습니다.");
    }
}
