package org.sopt.post.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record PostDetailResponse(
		long id,
		String title,
		String content,
		String author,
		LocalDateTime createdAt,
		boolean isAnonymous,
		List<String> hashtags,
		int likeCount,
		int commentCount,
		int scrapCount
) {
}
