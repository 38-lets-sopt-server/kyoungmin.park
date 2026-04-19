package org.sopt.post.service.dto.command;

import java.util.List;

import org.sopt.post.domain.BoardType;

public record CreatePostCommand(
		String title,
		String content,
		String author,
		BoardType boardType,
		boolean isAnonymous,
		List<String> hashtags
) {
	public CreatePostCommand(
			String title,
			String content,
			String author,
			String boardType,
			boolean isAnonymous,
			List<String> hashtags
	) {
		this(
				title,
				content,
				author,
				BoardType.from(boardType),
				isAnonymous,
				hashtags
		);
	}
}
