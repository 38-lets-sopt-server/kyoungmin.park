package org.sopt.post.service.mapper;

import java.time.LocalDateTime;

import org.sopt.post.domain.Post;
import org.sopt.post.dto.request.CreatePostRequest;
import org.sopt.post.dto.response.PostDetailResponse;
import org.sopt.post.dto.response.PostListResponse;

import java.util.List;

public class PostMapper {
	private PostMapper() {}

	public static Post toDomain(long id, CreatePostRequest request) {
		return new Post(
				id,
				request.title(),
				request.content(),
				request.author(),
				LocalDateTime.now(),
				request.isAnonymous(),
				request.hashtags(),
				0,
				0,
				0
		);
	}

	public static PostDetailResponse toDetailResponse(Post post) {
		return new PostDetailResponse(
				post.getId(),
				post.getTitle(),
				post.getContent(),
				post.getAuthor(),
				post.getCreatedAt(),
				post.isAnonymous(),
				post.getHashtags(),
				post.getLikeCount(),
				post.getCommentCount(),
				post.getScrapCount()
		);
	}

	public static PostListResponse toListResponse(List<Post> posts) {
		return PostListResponse.of(posts, posts.size());
	}
}
