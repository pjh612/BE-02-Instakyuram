package com.kdt.instakyuram.common;

import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Range;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.kdt.instakyuram.article.post.domain.PostPagingCursor;

public class PageDto {

	private PageDto() {
	}

	public record Request(@NotNull(message = "숫자를 입력해주세요")
						  @Positive Integer page,
						  @NotNull(message = "숫자를 입력해주세요")
						  @Range(min = 5, max = 10, message = "목록 단위는 5 ~ 10까지 가능합니다.") Integer size) {
		public Pageable getPageable(Sort sort) {
			return PageRequest.of(page - 1, size, sort);
		}
	}


	public static class PostCursorPageRequest {
		@Valid
		private PostPagingCursor cursor;
		private Integer size;

		public PostCursorPageRequest(PostPagingCursor cursor, Integer size) {
			this.cursor = cursor;
			this.size = size;
		}

		public PostCursorPageRequest() {
		}

		public PostPagingCursor getCursor() {
			return cursor;
		}

		public Integer getSize() {
			return size;
		}

		public void setCursor(PostPagingCursor cursor) {
			this.cursor = cursor;
		}

		public void setSize(Integer size) {
			this.size = size;
		}
	}

	public static class Response<DTO, DOMAIN> {
		private final List<DTO> responses;
		private final int totalPage;
		private final int page;
		private final int size;
		private final int start;
		private final int end;
		private final boolean hasPrevious;
		private final boolean hasNext;
		private final List<Integer> pageNumbers;

		public Response(Page<DOMAIN> result, Function<DOMAIN, DTO> toResponse) {
			responses = result.stream().map(toResponse).toList();
			totalPage = result.getTotalPages();

			this.page = result.getPageable().getPageNumber() + 1;
			this.size = result.getPageable().getPageSize();

			//temp end page
			int tempEnd = (int)(Math.ceil(page / (double)size)) * size;

			start = tempEnd - (size - 1);

			hasPrevious = start > 1;

			end = Math.min(totalPage, tempEnd);

			hasNext = totalPage > tempEnd;

			pageNumbers = IntStream.rangeClosed(start, end).boxed().toList();

		}

		public List<DTO> getResponses() {
			return responses;
		}

		public int getTotalPage() {
			return totalPage;
		}

		public int getPage() {
			return page;
		}

		public int getSize() {
			return size;
		}

		public int getStart() {
			return start;
		}

		public int getEnd() {
			return end;
		}

		public boolean hasPrevious() {
			return hasPrevious;
		}

		public boolean hasNext() {
			return hasNext;
		}

		public List<Integer> getPageNumbers() {
			return pageNumbers;
		}
	}

	public record CursorResponse<T, C>(List<T> values, Boolean hasNext, C cursor) {
	}
}
