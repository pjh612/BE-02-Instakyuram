package com.kdt.ggyustagram.post.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdt.ggyustagram.post.domain.Post;

public interface PostRepository extends JpaRepository<Post,Long> {
}
