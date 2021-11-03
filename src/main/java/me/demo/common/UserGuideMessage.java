package me.demo.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserGuideMessage {

    COMMON_SUCCESS("성공입니다."),

    COMMENT_NOT_EXISTS("존재하지 않은 comment 입니다."),
    ;

    private final String message;
}
