package com.ducpham.exception;

public class AppException extends RuntimeException {

    public AppException(EnumException exception, String message) {
        System.out.println(String.format("AppException: ERROR-%s : %s - %s", exception.getCode(), exception.getMsg(), message));
    }
}
