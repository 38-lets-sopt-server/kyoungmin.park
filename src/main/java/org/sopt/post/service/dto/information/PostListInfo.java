package org.sopt.post.service.dto.information;

import java.util.List;

import org.sopt.post.domain.Post;

public record PostListInfo(
		List<PostSummaryInfo> posts,
		long totalCount
) {
	public static PostListInfo of(List<Post> posts, int totalCount) {
		return new PostListInfo(
				posts.stream()
						.map(PostSummaryInfo::from)
						.toList(),
				totalCount
		);
	}
}
