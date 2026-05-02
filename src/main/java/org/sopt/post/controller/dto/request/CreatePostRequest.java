package org.sopt.post.controller.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreatePostRequest(
		@NotBlank
		String title,
		@NotBlank
		String content,
		@NotBlank
		String boardType,
		boolean isAnonymous
) {
}
