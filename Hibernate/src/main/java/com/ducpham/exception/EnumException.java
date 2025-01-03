package com.ducpham.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnumException{

    EXISTED("EE", "Existed Data : "),
    NOT_FOUND("NFE", "Not Found Data : "),
    SQL_EXCEPTION("SEE", "SQL Exception : "),
    BAD_REQUEST("BRE", "Bad Request : "),;

    private final String code;
    private final String message;
}
