package com.example.chocokcakeV2.global.error.data

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val message: String,
    val status: HttpStatus
) {
    //400
    DUPLICATED_MEMBER("Duplicated Member", HttpStatus.BAD_REQUEST),
    INCORRECT_PASSWORD("Incorrect Password", HttpStatus.BAD_REQUEST),
    ALREADY_EXIST_CAKE("ALREADY EXIST CAKE", HttpStatus.BAD_REQUEST),

    //401
    UN_AUTHORIZED("Un Authorized", HttpStatus.UNAUTHORIZED),
    TOKEN_CAN_NOT_BE_NULL("Token Can Not Be Null", HttpStatus.UNAUTHORIZED),

    //403
    NO_PERMISSIONS("No Permissions", HttpStatus.FORBIDDEN),
    FORBIDDEN("Forbidden", HttpStatus.FORBIDDEN),

    //404
    USER_NOT_FOUND("User Not Found", HttpStatus.NOT_FOUND),
    CAKE_NOT_FOUND("Cake Not Found", HttpStatus.NOT_FOUND),

    //500
    INTERNAL_SERVER_ERROR("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
}