package org.sopt.post.controller.dto.response;

public record PostSummaryResponse(
		long id,
		String title,
		String content,
		int likeCount,
		int commentCount,
		String createdAt,
		String author
) {
}
