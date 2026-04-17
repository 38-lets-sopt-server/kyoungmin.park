package org.sopt.post.service.dto.command;

import java.util.List;

public record CreatePostCommand(
		String title,
		String content,
		String author,
		boolean isAnonymous,
		List<String> hashtags
) {
}
