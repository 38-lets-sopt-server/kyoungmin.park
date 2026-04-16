package org.sopt.post.controller.dto.request;

import org.sopt.global.status.FailureStatus;
import org.sopt.post.exception.InvalidContentException;
import org.sopt.post.exception.InvalidTitleException;

public record UpdatePostRequest(
		long id,
		String title,
		String content,
		String hashtags
) {
	public void validate() {
		if (title == null || title.isBlank()) {
			throw new InvalidTitleException(FailureStatus.TITLE_REQUIRED);
		}
		if (content == null || content.isBlank()) {
			throw new InvalidContentException();
		}
	}
}
