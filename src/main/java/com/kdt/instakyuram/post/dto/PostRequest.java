package com.kdt.instakyuram.post.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public record PostRequest() {

	public record CreateRequest(
		Long memberId,
		String content,
		List<MultipartFile> postImages
	) { }

}