package org.sopt.post.model.input;

import org.sopt.post.exception.InvalidAnonymousFlagException;
import org.sopt.post.exception.InvalidAuthorException;
import org.sopt.post.exception.InvalidContentException;
import org.sopt.post.exception.InvalidTitleException;

public record CreatePostInput(
		String title,
		String content,
		String author,
		String isAnonymous,
		String hashtags
) {
	public void validate() {
		if (title == null || title.isBlank()) {
			throw new InvalidTitleException();
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
