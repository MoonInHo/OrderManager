package com.foodtech.mate.exception.exception.member;

public class SamePasswordException extends RuntimeException {

    public SamePasswordException() {
        super("기존 비밀번호로 변경하실 수 없습니다.");
    }
}
