package me.demo.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(CommonException.class)
    public CommonResponse<Void> commonException(CommonException exception) {
        return CommonResponse.<Void>builder()
                .message(exception.getUserGuideMessage().getMessage())
                .build();
    }
}
