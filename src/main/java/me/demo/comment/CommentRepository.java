package me.demo.comment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByBoardIdAndWik2Id(Long boardId, Long wik2Id);
}
