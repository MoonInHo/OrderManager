package com.mooninho.ordermanager.exception;

public enum ErrorCode {

    // Member
    NOT_FOUND_USER_ERROR,
    DUPLICATE_USERID_ERROR,
    DUPLICATE_PHONE_ERROR,

    // Store
    DUPLICATE_STORE_NAME_ERROR,

    // Menu
    DUPLICATE_MENU_NAME_ERROR,

    // Customer
    DUPLICATE_CONTACT_ERROR,

    // Global
    INVALID_REQUEST,
    UNAUTHORIZED_POSTING_ERROR,
}