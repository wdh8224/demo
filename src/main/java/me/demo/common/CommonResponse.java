package me.demo.common;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommonResponse<T> {

    @Builder.Default
    private final String message = "success";
    private final T data;
}
