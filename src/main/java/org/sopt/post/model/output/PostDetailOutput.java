package org.sopt.post.model.output;

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
