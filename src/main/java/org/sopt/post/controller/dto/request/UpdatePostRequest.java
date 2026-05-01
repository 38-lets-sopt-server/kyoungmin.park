package org.sopt.post.controller.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UpdatePostRequest(
		@NotBlank
		String title,
		@NotBlank
		String content
) {
}
