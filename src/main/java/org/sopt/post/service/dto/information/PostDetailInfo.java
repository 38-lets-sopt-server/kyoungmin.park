package org.sopt.post.service.dto.information;

import java.time.LocalDateTime;

import org.sopt.post.entity.BoardType;

public record PostDetailInfo(
		long id,
		String title,
		String content,
		BoardType boardType,
		String author,
		LocalDateTime createdAt,
		boolean isAnonymous,
		int likeCount,
		int commentCount,
		int scrapCount
) {
}
