package com.kdt.ggyustagram.postImage.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.kdt.ggyustagram.post.domain.Post;

@Entity
public class PostImage {

	@Id
	private Long id;

	@ManyToOne
	@JoinColumn(name = "post_id", referencedColumnName = "id")
	private Post post;

	private String originalFileName;

	private String serverFileName;

	private Long size;

	private String path;
}
