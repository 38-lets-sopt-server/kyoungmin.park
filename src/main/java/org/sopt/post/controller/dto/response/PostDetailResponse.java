package org.sopt.post.controller.dto.response;

public record PostDetailResponse(
		long id,
		String title,
		String content,
		String boardType,
		String author,
		String createdAt,
		boolean isAnonymous,
		int likeCount,
		int commentCount,
		int scrapCount
) {
}
