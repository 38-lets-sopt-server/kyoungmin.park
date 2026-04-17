package org.sopt.post.controller.dto.response;

import java.util.List;

public record PostDetailResponse(
		long id,
		String title,
		String content,
		String author,
		String createdAt,
		boolean isAnonymous,
		List<String> hashtags,
		int likeCount,
		int commentCount,
		int scrapCount
) {
}
