package org.sopt.post.controller.dto.response;

public record PostDetailResponse(
		long id,
		String title,
		String content,
		String author,
		String createdAt,
		boolean isAnonymous,
		String hashtags,
		int likeCount,
		int commentCount,
		int scrapCount
) {
}
