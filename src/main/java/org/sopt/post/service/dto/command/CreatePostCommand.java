package org.sopt.post.service.dto.command;

import java.util.Arrays;
import java.util.List;

public record CreatePostCommand(
		String title,
		String content,
		String author,
		boolean isAnonymous,
		List<String> hashtags
) {
	public CreatePostCommand(
			String title,
			String content,
			String author,
			String isAnonymous,
			String hashtags
	) {
		this(
				title,
				content,
				author,
				isAnonymous.equalsIgnoreCase("Y"),
				Arrays.stream(hashtags.split(","))
						.map(String::trim)
						.toList()
		);
	}
}
