package org.sopt.post.service.mapper;

import org.sopt.member.entity.Member;
import org.sopt.post.entity.Post;
import org.sopt.post.service.dto.command.CreatePostCommand;
import org.sopt.post.service.dto.information.PostDetailInfo;
import org.sopt.post.service.dto.information.PostListInfo;
import org.sopt.post.service.dto.information.PostSummaryInfo;

import java.util.List;

public class PostMapper {
	private PostMapper() {}

	public static Post toDomain(CreatePostCommand request, Member member) {
		return new Post(
				request.title(),
				request.content(),
				request.boardType(),
				request.isAnonymous(),
				0,
				0,
				0,
				member
		);
	}

	public static PostDetailInfo toDetailInfo(Post post) {
		return new PostDetailInfo(
				post.getId(),
				post.getTitle(),
				post.getContent(),
				post.getBoardType(),
				resolveAuthorName(post),
				post.getCreatedAt(),
				post.isAnonymous(),
				post.getLikeCount(),
				post.getCommentCount(),
				post.getScrapCount()
		);
	}

	public static PostListInfo toListInfo(List<Post> posts, int currentPage, boolean hasNext) {
		List<PostSummaryInfo> postSummaries = posts.stream()
				.map(PostMapper::toSummaryInfo)
				.toList();

		return new PostListInfo(postSummaries, currentPage, hasNext);
	}

	private static PostSummaryInfo toSummaryInfo(Post post) {
		return new PostSummaryInfo(
				post.getId(),
				post.getTitle(),
				post.getContent(),
				post.getLikeCount(),
				post.getCommentCount(),
				post.getCreatedAt(),
				resolveAuthorName(post),
				post.getBoardType()
		);
	}

	private static String resolveAuthorName(Post post) {
		if(post.isAnonymous()){
			return "익명";
		}
		return post.getMember().getNickname();
	}
}
