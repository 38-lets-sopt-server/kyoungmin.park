package org.sopt.post.controller.dto.request;

import org.sopt.post.code.FailureCode;
import org.sopt.post.exception.InvalidContentException;
import org.sopt.post.exception.InvalidTitleException;

public record UpdatePostRequest(
		String title,
		String content
) {
	public UpdatePostRequest {
		this.validate(title, content);
	}

	private void validate(String title, String content) {
		if (title == null || title.isBlank()) {
			throw new InvalidTitleException(FailureCode.TITLE_REQUIRED);
		}
		if (content == null || content.isBlank()) {
			throw new InvalidContentException();
		}
	}
}
