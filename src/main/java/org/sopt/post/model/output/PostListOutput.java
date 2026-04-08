package org.sopt.post.model.output;

import java.util.List;

public record PostListOutput(
		List<PostSummaryOutput> posts,
		long totalCount
) {
}
