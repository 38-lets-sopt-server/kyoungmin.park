package org.sopt.post.service.dto.command;

import java.util.List;

public record UpdatePostCommand(
		String title,
		String content,
		List<String> hashtags
) {
}
