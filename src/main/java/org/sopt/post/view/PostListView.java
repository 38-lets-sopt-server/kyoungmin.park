package org.sopt.post.view;

import org.sopt.global.response.CommonResponse;
import org.sopt.post.model.output.PostListOutput;
import org.sopt.post.model.output.PostSummaryOutput;

public class PostListView {
	public static void printPostList(CommonResponse<PostListOutput> response) {
		if (response.isSuccess()) {
			PostListOutput data = response.data();
			System.out.println("\n" + response.message());
			System.out.println("\n전체 게시글 수: " + data.totalCount());

			if (data.posts().isEmpty()) {
				System.out.println("\n등록된 게시글이 없습니다.");
				return;
			}

			System.out.println("\n=== [게시글 목록] ===");

			for (PostSummaryOutput post : data.posts()) {
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
		} else {
			System.out.println("\n[요청 처리에 실패했습니다] : " + response.message());
		}
	}

	private static String truncateText(String text) {
		if (text.length() > 20) {
			return text.substring(0, 17) + "...";
		}
		return text;
	}
}
