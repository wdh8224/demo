package me.demo.comment;

import lombok.RequiredArgsConstructor;
import me.demo.common.CommonResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/comments/{commentId}")
    public CommonResponse<Comment> comment(@PathVariable Long commentId) {
        Comment comment = commentService.getComment(commentId);
        return CommonResponse.<Comment>builder()
                .data(comment)
                .build();
    }

    @GetMapping("/comments")
    public CommonResponse<List<Comment>> comments(CommentSearchParam param) {
        List<Comment> comments = commentService.getComments(param);
        return CommonResponse.<List<Comment>>builder()
                .data(comments)
                .build();
    }

    @PostMapping("/comments")
    public CommonResponse<Void> insert(@RequestBody CommentCreateBody body) {
        commentService.save(body);
        return CommonResponse.<Void>builder()
                .build();
    }

    @PatchMapping("/comments/{commentId}")
    public CommonResponse<Void> update(@PathVariable Long commentId, @RequestBody CommentUpdateBody body) {
        commentService.update(commentId, body);
        return CommonResponse.<Void>builder()
                .build();
    }

    @DeleteMapping("/comments/{commentId}")
    public CommonResponse<Void> delete(@PathVariable Long commentId) {
        commentService.delete(commentId);
        return CommonResponse.<Void>builder()
                .build();
    }
}
