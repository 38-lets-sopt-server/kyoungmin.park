package org.sopt.post.service.dto.information;

import java.time.LocalDateTime;
import java.util.List;

public record PostDetailInfo(
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
