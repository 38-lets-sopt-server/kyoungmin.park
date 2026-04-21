package org.sopt.post.service.dto.information;

import java.time.LocalDateTime;

import org.sopt.post.domain.BoardType;
import org.sopt.post.domain.Post;

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
	public static PostSummaryInfo from(Post post) {
		return new PostSummaryInfo(
				post.getId(),
				post.getTitle(),
				post.getContent(),
				post.getLikeCount(),
				post.getCommentCount(),
				post.getCreatedAt(),
				post.getAuthor(),
				post.getBoardType()
		);
	}
}
