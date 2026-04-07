package org.sopt.post.controller.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

import org.sopt.post.dto.request.CreatePostRequest;
import org.sopt.post.dto.response.PostDetailResponse;
import org.sopt.post.model.input.CreatePostInput;
import org.sopt.post.model.output.PostDetailOutput;

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

	public static PostDetailOutput toOutput(PostDetailResponse response) {
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

	private static String formatHashtags(java.util.List<String> hashtags) {
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
