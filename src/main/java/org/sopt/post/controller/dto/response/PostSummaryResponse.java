package org.sopt.post.controller.dto.response;

public record PostSummaryResponse(
		long id,
		String title,
		String content,
		String boardType,
		int likeCount,
		int commentCount,
		String createdAt,
		String author
) {
}
