package me.demo.comment;

import org.springframework.http.HttpStatus;

public class CommentException extends RuntimeException {

    private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    private String errorMessage;

    public CommentException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public CommentException(HttpStatus httpStatus, String errorMessage) {
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }
}
