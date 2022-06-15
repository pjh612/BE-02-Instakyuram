package com.kdt.ggyustagram.post.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.kdt.ggyustagram.member.domain.Member;

@Entity
public class Post {
	@Id
	private Long id;

	private String content;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private Member user;

}
