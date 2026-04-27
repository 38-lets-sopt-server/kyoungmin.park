package org.sopt.post.service.dto.information;

import java.util.List;

public record PostListInfo(
		List<PostSummaryInfo> posts,
		long totalCount
) {
}
