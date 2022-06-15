package com.kdt.ggyustagram.commentlike.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.kdt.ggyustagram.comment.domain.Comment;
import com.kdt.ggyustagram.member.domain.Member;

@Entity
public class CommentLike {

	@Id
	private Long id;

	@JoinColumn(name = "comment_id", referencedColumnName = "id")
	@ManyToOne
	private Comment comment;

	@JoinColumn(name= "user_id", referencedColumnName = "id")
	@ManyToOne
	private Member user;
}
