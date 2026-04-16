package org.sopt.post.controller.dto.response;

import java.util.List;

public record PostListResponse(
		List<PostSummaryResponse> posts,
		long totalCount
) {
}
