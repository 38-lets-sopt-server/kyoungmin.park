package org.sopt.post.service.dto.command;

import java.util.Arrays;
import java.util.List;

public record UpdatePostCommand(
		String title,
		String content,
		List<String> hashtags
) {
	public UpdatePostCommand(
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
