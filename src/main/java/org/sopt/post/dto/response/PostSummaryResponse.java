package org.sopt.post.dto.response;

import java.time.LocalDateTime;

import org.sopt.post.domain.Post;

public record PostSummaryResponse(
		long id,
		String title,
		String content,
		int likeCount,
		int commentCount,
		LocalDateTime createdAt,
		String author
) {
	public static PostSummaryResponse from(Post post) {
		return new PostSummaryResponse(
				post.getId(),
				post.getTitle(),
				post.getContent(),
				post.getLikeCount(),
				post.getCommentCount(),
				post.getCreatedAt(),
				post.getAuthor()
		);
	}
}
