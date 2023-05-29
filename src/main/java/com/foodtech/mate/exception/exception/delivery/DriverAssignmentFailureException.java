package com.foodtech.mate.exception.exception.delivery;

public class DriverAssignmentFailureException extends RuntimeException {

    public DriverAssignmentFailureException() {
        super("기사 배정에 실패했습니다.");
    }
}
