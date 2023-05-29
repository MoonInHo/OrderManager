package com.foodtech.mate.exception.exception.member;

public class PhoneExistException extends RuntimeException {

    public PhoneExistException() {
        super("해당 연락처로 가입정보가 존재합니다.");
    }
}
