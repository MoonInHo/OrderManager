package com.order.manager.util.validation;

public class PatternMatcher {

    public static boolean isInvalidUserIdFormat(String userId) {
        return !userId.matches("^[a-zA-Z0-9]{5,}$");
    }
    public static boolean isInvalidPasswordFormat(String password) {
        return !password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%&*?])[A-Za-z\\d!@#$%&*?]{10,20}$");
    }
    public static boolean isInvalidNameFormat(String name) {
        return !name.matches("^[가-힣]{2,5}$");
    }
    public static boolean isInvalidPhoneFormat(String phone) {
        return !phone.matches("^010-(?:\\d{3}|\\d{4})-\\d{4}$");
    }
}
