package com.sts.util;

import lombok.Getter;

@Getter
public enum ErrorCode {

    EMAIL_NOT_PROVIDED(409, "Email is not provided"),
    EMAIL_ALREADY_EXISTS(409, "Email already exists"),
    VERIFICATION_TOKEN_INVALID(422, "Verification token is invalid or expired");

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
