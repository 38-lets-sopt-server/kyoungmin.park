package org.sopt.post.controller.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.sopt.post.dto.request.CreatePostRequest;
import org.sopt.post.dto.request.UpdatePostRequest;
import org.sopt.post.dto.response.PostDetailResponse;
import org.sopt.post.dto.response.PostListResponse;
import org.sopt.post.model.input.CreatePostInput;
import org.sopt.post.model.input.UpdatePostInput;
import org.sopt.post.model.output.PostDetailOutput;
import org.sopt.post.model.output.PostListOutput;
import org.sopt.post.model.output.PostSummaryOutput;

public class PostPresentationMapper {
	private static final DateTimeFormatter TIME_FORMATTER =
			DateTimeFormatter.ofPattern("HH:mm");
	private static final DateTimeFormatter DATE_FORMATTER =
			DateTimeFormatter.ofPattern("MM/dd");
	private static final DateTimeFormatter ENTIRE_FORMATTER =
			DateTimeFormatter.ofPattern("yy/MM/dd HH:mm");

	public static CreatePostRequest toRequest(CreatePostInput input) {
		return new CreatePostRequest(
				input.title(),
				input.content(),
				input.author(),
				input.isAnonymous(),
				input.hashtags()
		);
	}

	public static UpdatePostRequest toRequest(UpdatePostInput input) {
		return new UpdatePostRequest(
				input.title(),
				input.content(),
				input.hashtags()
		);
	}

	public static PostDetailOutput toDetailOutput(PostDetailResponse response) {
		return new PostDetailOutput(
				response.id(),
				response.title(),
				response.content(),
				response.author(),
				formatEntireCreatedAt(response.createdAt()),
				response.isAnonymous(),
				formatHashtags(response.hashtags()),
				response.likeCount(),
				response.commentCount(),
				response.scrapCount()
		);
	}

	public static PostListOutput toListOutput(PostListResponse response) {
		List<PostSummaryOutput> summaries = response.posts().stream()
				.map((summaryResponse) -> new PostSummaryOutput(
						summaryResponse.id(),
						summaryResponse.title(),
						summaryResponse.content(),
						summaryResponse.likeCount(),
						summaryResponse.commentCount(),
						formatCreatedAt(summaryResponse.createdAt()),
						summaryResponse.author()
				)).toList();
		return new PostListOutput(summaries, response.totalCount());
	}

	private static String formatHashtags(List<String> hashtags) {
		if (hashtags == null || hashtags.isEmpty()) {
			return "";
		}
		return hashtags.stream()
				.map(tag -> "#" + tag)
				.collect(Collectors.joining(" "));
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
