package org.sopt.post.controller.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.sopt.post.controller.dto.request.CreatePostRequest;
import org.sopt.post.controller.dto.request.UpdatePostRequest;
import org.sopt.post.controller.dto.response.PostDetailResponse;
import org.sopt.post.controller.dto.response.PostListResponse;
import org.sopt.post.controller.dto.response.PostSummaryResponse;
import org.sopt.post.service.dto.command.CreatePostCommand;
import org.sopt.post.service.dto.command.UpdatePostCommand;
import org.sopt.post.service.dto.information.PostDetailInfo;
import org.sopt.post.service.dto.information.PostListInfo;

public class PostPresentationMapper {
	private PostPresentationMapper() {}

	private static final DateTimeFormatter TIME_FORMATTER =
			DateTimeFormatter.ofPattern("HH:mm");
	private static final DateTimeFormatter DATE_FORMATTER =
			DateTimeFormatter.ofPattern("MM/dd");
	private static final DateTimeFormatter ENTIRE_FORMATTER =
			DateTimeFormatter.ofPattern("yy/MM/dd HH:mm");

	public static CreatePostCommand toCommand(CreatePostRequest request) {
		return new CreatePostCommand(
				request.title(),
				request.content(),
				request.author(),
				request.boardType(),
				request.isAnonymous(),
				request.hashtags()
		);
	}

	public static UpdatePostCommand toCommand(UpdatePostRequest request) {
		return new UpdatePostCommand(
				request.title(),
				request.content(),
				request.hashtags()
		);
	}

	public static PostDetailResponse toDetailResponse(PostDetailInfo info) {
		return new PostDetailResponse(
				info.id(),
				info.title(),
				info.content(),
				info.author(),
				formatEntireCreatedAt(info.createdAt()),
				info.isAnonymous(),
				info.hashtags(),
				info.likeCount(),
				info.commentCount(),
				info.scrapCount()
		);
	}

	public static PostListResponse toListResponse(PostListInfo info) {
		List<PostSummaryResponse> summaries = info.posts().stream()
				.map((summaryResponse) -> new PostSummaryResponse(
						summaryResponse.id(),
						summaryResponse.title(),
						summaryResponse.content(),
						summaryResponse.likeCount(),
						summaryResponse.commentCount(),
						formatCreatedAt(summaryResponse.createdAt()),
						summaryResponse.author()
				)).toList();
		return new PostListResponse(summaries, info.totalCount());
	}

	private static String formatCreatedAt(LocalDateTime createdAt) {
		LocalDateTime now = LocalDateTime.now();

		if (createdAt.toLocalDate().equals(now.toLocalDate())) {
			return createdAt.format(TIME_FORMATTER);
		} else if (createdAt.getYear() == now.getYear()) {
			return createdAt.format(DATE_FORMATTER);
		} else {
			int yearDiff = now.getYear() - createdAt.getYear();
			return yearDiff + "년 전";
		}
	}

	private static String formatEntireCreatedAt(LocalDateTime createdAt) {
		return createdAt.format(ENTIRE_FORMATTER);
	}
}
