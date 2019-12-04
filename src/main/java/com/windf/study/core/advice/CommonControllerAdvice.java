package com.windf.study.core.advice;

import com.windf.study.core.bean.RestResult;
import com.windf.study.core.exception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CommonControllerAdvice {

    @ExceptionHandler(value = {UserException.class})
    public RestResult handleUserException(Throwable throwable) {
        return RestResult.result(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                throwable.getMessage());
    }

}
