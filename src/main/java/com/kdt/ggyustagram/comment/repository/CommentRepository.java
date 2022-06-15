package com.kdt.ggyustagram.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdt.ggyustagram.comment.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
