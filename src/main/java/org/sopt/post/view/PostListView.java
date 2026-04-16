package org.sopt.post.view;

import org.sopt.global.response.CommonResponse;
import org.sopt.post.controller.dto.response.PostListResponse;
import org.sopt.post.controller.dto.response.PostSummaryResponse;

public class PostListView {
	public static void printPostList(CommonResponse<PostListResponse> response) {
		if (response.isSuccess()) {
			PostListResponse data = response.data();
			System.out.println("\n" + response.message());
			System.out.println("\n전체 게시글 수: " + data.totalCount());

			if (data.posts().isEmpty()) {
				System.out.println("\n등록된 게시글이 없습니다.");
				return;
			}

			System.out.println("\n=== [게시글 목록] ===");

			for (PostSummaryResponse post : data.posts()) {
				printPostListcomponent(post);
			}
		} else {
			System.out.println("\n[요청 처리에 실패했습니다] : " + response.message());
		}
	}

	private static void printPostListcomponent(PostSummaryResponse post) {
		System.out.printf("ID: %d%n", post.id());
		System.out.printf("제목: %-20s%n", truncateText(post.title()));
		System.out.printf("내용: %-20s%n", truncateText(post.content()));
		System.out.printf("공감수: %d | 댓글수: %d | %s | %s%n",
				post.likeCount(),
				post.commentCount(),
				post.createdAt(),
				post.author()
		);
		System.out.println("-------------------------------------");
	}

	private static String truncateText(String text) {
		if (text.length() > 20) {
			return text.substring(0, 17) + "...";
		}
		return text;
	}
}
