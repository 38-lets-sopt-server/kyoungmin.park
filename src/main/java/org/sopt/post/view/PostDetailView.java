package org.sopt.post.view;

import org.sopt.global.response.CommonResponse;
import org.sopt.post.model.output.PostDetailOutput;

public class PostDetailView {
	public static Long getPostId(java.util.Scanner scanner) {
		System.out.print("조회할 게시글 ID: ");
		Long id = scanner.nextLong();
		scanner.nextLine();
		return id;
	}

	public static void printPostDetail(CommonResponse<PostDetailOutput> response) {
		if (response.isSuccess()) {
			System.out.println("\n" + response.message());
			PostDetailOutput data = response.data();
			System.out.println("\n=== [게시글 상세 정보] ===");
			System.out.println("ID: " + data.id());
			System.out.println("작성자: " + data.author());
			System.out.println("작성일: " + data.createdAt());
			System.out.println("----------------------");
			System.out.println("제목: " + data.title());
			System.out.println("내용: " + data.content());
			if (data.hashtags() != null && !data.hashtags().isEmpty()) {
				System.out.println("해시태그: " + data.hashtags());
			}
			System.out.println("공감: " + data.likeCount() + "\t댓글: " + data.commentCount() + "\t스크랩: " + data.scrapCount());
			System.out.println("======================");
		} else {
			System.out.println("\n[요청 처리에 실패했습니다] : " + response.message());
		}
	}
}
