package org.sopt.post.dto.request;

import java.util.Arrays;
import java.util.List;

public record CreatePostRequest(
		String title,
		String content,
		String author,
		boolean isAnonymous,
		List<String> hashtags
) {
	public CreatePostRequest(
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