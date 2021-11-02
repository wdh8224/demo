package me.demo.comment;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Entity
public class Comment {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long commentId;
    private Long boardID;

    private Long wik2ID;
    private String userID;
    private String commentDate;
    private String commentText;
    private int commentAvailable;
}