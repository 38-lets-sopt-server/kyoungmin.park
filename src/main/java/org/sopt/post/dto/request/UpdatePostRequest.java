package org.sopt.post.dto.request;

import java.util.Arrays;
import java.util.List;

public record UpdatePostRequest(
		String title,
		String content,
		List<String> hashtags
) {
	public UpdatePostRequest(
			String title,
			String content,
			String hashtags
	) {
		this(
				title,
				content,
				Arrays.stream(hashtags.split(","))
						.map(String::trim)
						.filter(tag -> !tag.isEmpty())
						.toList()
		);
	}
}
