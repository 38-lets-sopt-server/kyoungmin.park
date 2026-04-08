package org.sopt.post.dto.response;

import java.util.List;

import org.sopt.post.domain.Post;

public record PostListResponse(
		List<PostSummaryResponse> posts,
		long totalCount
) {
	public static PostListResponse of(List<Post> posts, int totalCount) {
		return new PostListResponse(
				posts.stream()
						.map(PostSummaryResponse::from)
						.toList(),
				totalCount
		);
	}
}
