package org.sopt.post.service.dto.information;

import java.time.LocalDateTime;
import java.util.List;

import org.sopt.post.domain.BoardType;

public record PostDetailInfo(
		long id,
		String title,
		String content,
		BoardType boardType,
		String author,
		LocalDateTime createdAt,
		boolean isAnonymous,
		List<String> hashtags,
		int likeCount,
		int commentCount,
		int scrapCount
) {
}
