package org.sopt.post.service.dto.command;

public record UpdatePostCommand(
		String title,
		String content
) {
}
