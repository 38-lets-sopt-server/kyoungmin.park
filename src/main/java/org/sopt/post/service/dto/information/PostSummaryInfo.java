package org.sopt.post.service.dto.information;

import java.time.LocalDateTime;

import org.sopt.post.entity.BoardType;

public record PostSummaryInfo(
		long id,
		String title,
		String content,
		int likeCount,
		int commentCount,
		LocalDateTime createdAt,
		String author,
		BoardType boardType
) {
}
