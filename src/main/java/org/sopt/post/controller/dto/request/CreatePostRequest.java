package org.sopt.post.controller.dto.request;

import org.sopt.global.status.FailureStatus;
import org.sopt.post.exception.InvalidAnonymousFlagException;
import org.sopt.post.exception.InvalidAuthorException;
import org.sopt.post.exception.InvalidContentException;
import org.sopt.post.exception.InvalidTitleException;

public record CreatePostRequest(
		String title,
		String content,
		String author,
		String isAnonymous,
		String hashtags
) {
	public CreatePostRequest {
		this.validate(title, content, author, isAnonymous);
	}

	private void validate(String title, String content, String author, String isAnonymous) {
		if (title == null || title.isBlank()) {
			throw new InvalidTitleException(FailureStatus.TITLE_REQUIRED);
		}
		if (content == null || content.isBlank()) {
			throw new InvalidContentException();
		}
		if (author == null || author.isBlank()) {
			throw new InvalidAuthorException();
		}

		if (isAnonymous == null ||
				(!isAnonymous.equalsIgnoreCase("Y") &&
						!isAnonymous.equalsIgnoreCase("N"))) {
			throw new InvalidAnonymousFlagException();
		}
	}
}
