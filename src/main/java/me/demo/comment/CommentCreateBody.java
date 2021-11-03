package me.demo.comment;

import lombok.Getter;

@Getter
public class CommentCreateBody {

    private Long boardId;
    private Long wik2Id;
    private String userId;
    private String commentText;
}
