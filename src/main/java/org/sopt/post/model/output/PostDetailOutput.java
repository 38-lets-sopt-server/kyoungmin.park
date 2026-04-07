package org.sopt.post.model.output;

import java.time.LocalDateTime;
import java.util.List;

public record PostDetailOutput(
		long id,
		String title,
		String content,
		String author,
		String createdAt,
		boolean isAnonymous,
		String hashtags,
		int likeCount,
		int commentCount,
		int scrapCount
) {
}
