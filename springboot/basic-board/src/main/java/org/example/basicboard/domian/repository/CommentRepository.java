package org.example.basicboard.domian.repository;

import org.example.basicboard.domian.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}