package org.sopt.post.model.output;

public record PostSummaryOutput(
		long id,
		String title,
		String content,
		int likeCount,
		int commentCount,
		String createdAt,
		String author
) {
}
