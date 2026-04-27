package org.sopt.post.service.dto.command;

import org.sopt.post.entity.BoardType;

public record CreatePostCommand(
		String title,
		String content,
		String author,
		BoardType boardType,
		boolean isAnonymous
) {
	public CreatePostCommand(
			String title,
			String content,
			String author,
			String boardType,
			boolean isAnonymous
	) {
		this(
				title,
				content,
				author,
				BoardType.from(boardType),
				isAnonymous
		);
	}
}
