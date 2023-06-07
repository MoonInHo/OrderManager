package com.order.manager.exception.exception.member;

public class UserIdExistException extends RuntimeException {

    public UserIdExistException() {
        super("! 이미 사용중인 아이디입니다.");
    }
}
