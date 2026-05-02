package org.sopt.post.service.dto.command;

import org.sopt.post.entity.BoardType;

public record CreatePostCommand(
		String title,
		String content,
		BoardType boardType,
		boolean isAnonymous
) {
	public CreatePostCommand(
			String title,
			String content,
			String boardType,
			boolean isAnonymous
	) {
		this(
				title,
				content,
				BoardType.from(boardType),
				isAnonymous
		);
	}
}
