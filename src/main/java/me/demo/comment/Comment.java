package me.demo.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PACKAGE)
public class Comment {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long commentId;
    private Long boardId;
    private Long wik2Id;
    private String userId;

    private LocalDateTime commentDate;
    private String commentText;
    private int commentAvailable;

    @PrePersist
    public void prePersist() {
        this.commentDate = LocalDateTime.now();
    }

    public void updateCommentText(String commentText) {
        this.commentText = commentText;
    }
}