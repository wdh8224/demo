package me.demo.comment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.demo.common.CommonException;
import me.demo.common.UserGuideMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository comments;

    public Comment getComment(Long commentId) {
        return comments.findById(commentId)
                .orElseThrow(() -> new CommonException(UserGuideMessage.COMMENT_NOT_EXISTS));
    }

    public List<Comment> getComments(CommentSearchParam param) {
        Long boardId = param.getBoardId();
        Long wik2Id = param.getWik2Id();

        return comments.findByBoardIdAndWik2Id(boardId, wik2Id);
    }

    @Transactional
    public void save(CommentCreateBody body) {
        Long boardId = body.getBoardId();
        Long wik2Id = body.getWik2Id();
        String userId = body.getUserId();
        String commentText = body.getCommentText();

        Comment comment = Comment.builder()
                .boardId(boardId)
                .wik2Id(wik2Id)
                .userId(userId)
                .commentText(commentText)
                .commentAvailable(1)
                .build();

        Comment saved = comments.save(comment);
        log.info("Successfully saved = {}", saved);
    }

    @Transactional
    public void update(Long commentId, CommentUpdateBody body) {
        Comment comment = getComment(commentId);

        String commentText = body.getCommentText();
        comment.updateCommentText(commentText);

        Comment updated = comments.save(comment);
        log.info("Successfully updated = {}", updated);
    }

    @Transactional
    public void delete(Long commentId) {
        Comment comment = getComment(commentId);

        comments.delete(comment);
        log.info("Successfully deleted = {}", comment);
    }
}
