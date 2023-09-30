package com.mooninho.ordermanager.ownerapp.exception;

public enum ErrorCode {

    // Global
    INVALID_REQUEST,
    UNAUTHORIZED_POSTING_ERROR,

    // Member
    NOT_FOUND_USER_ERROR,
    DUPLICATE_USERNAME_ERROR,
    DUPLICATE_PHONE_ERROR,

    // Store
    DUPLICATE_STORE_NAME_ERROR,

    // Menu
    DUPLICATE_MENU_NAME_ERROR,

    // Customer
    DUPLICATE_CONTACT_ERROR,

    // Order
    EMPTY_ORDER_LIST_ERROR,
    NOT_FOUND_ORDER_ERROR,

    // Delivery
    NOT_FOUND_DELIVERY_COMPANY_ERROR,

    // DeliveryCompany
    DUPLICATE_COMPANY_NAME_ERROR,
}