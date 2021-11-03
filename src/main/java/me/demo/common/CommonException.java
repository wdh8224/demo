package me.demo.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CommonException extends RuntimeException {

    private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    private UserGuideMessage userGuideMessage;

    public CommonException(UserGuideMessage userGuideMessage) {
        this.userGuideMessage = userGuideMessage;
    }

    public CommonException(HttpStatus httpStatus, UserGuideMessage userGuideMessage) {
        this.httpStatus = httpStatus;
        this.userGuideMessage = userGuideMessage;
    }
}
