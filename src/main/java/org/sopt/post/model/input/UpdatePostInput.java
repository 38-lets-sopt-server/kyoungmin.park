package org.sopt.post.model.input;

import org.sopt.post.exception.InvalidContentException;
import org.sopt.post.exception.InvalidTitleException;

public record UpdatePostInput(
		long id,
		String title,
		String content,
		String hashtags
) {
	public void validate() {
		if (title == null || title.isBlank()) {
			throw new InvalidTitleException();
		}
		if (content == null || content.isBlank()) {
			throw new InvalidContentException();
		}
	}
}
