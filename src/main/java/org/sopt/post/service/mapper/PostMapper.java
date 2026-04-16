package org.sopt.post.service.mapper;

import java.time.LocalDateTime;

import org.sopt.post.domain.Post;
import org.sopt.post.service.dto.command.CreatePostCommand;
import org.sopt.post.service.dto.information.PostDetailInfo;
import org.sopt.post.service.dto.information.PostListInfo;

import java.util.List;

public class PostMapper {
	private PostMapper() {}

	public static Post toDomain(long id, CreatePostCommand request) {
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

	public static PostDetailInfo toDetailInfo(Post post) {
		return new PostDetailInfo(
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

	public static PostListInfo toListInfo(List<Post> posts) {
		return PostListInfo.of(posts, posts.size());
	}
}
