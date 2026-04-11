package org.sopt.post.view;

import java.util.Scanner;

import org.sopt.global.response.CommonResponse;
import org.sopt.post.model.output.PostDetailOutput;

public class PostDetailView {
	public static long getPostId(Scanner scanner) {
		printEnterPostIdMsg();
		long id = scanner.nextLong();
		scanner.nextLine();
		return id;
	}

	public static void printPostDetail(CommonResponse<PostDetailOutput> response) {
		if (response.isSuccess()) {
			System.out.println("\n" + response.message());
			PostDetailOutput post = response.data();
			printPostDetailcomponent(post);
		} else {
			System.out.println("\n[요청 처리에 실패했습니다] : " + response.message());
		}
	}

	private static void printEnterPostIdMsg() {
		System.out.println("\n조회할 게시물의 ID를 입력해주세요.");
		System.out.print("> ");
	}

	private static void printPostDetailcomponent(PostDetailOutput post) {
		System.out.println("\n=== [게시글 상세 정보] ===");
		System.out.println("ID: " + post.id());
		System.out.println("작성자: " + post.author());
		System.out.println("작성일: " + post.createdAt());
		System.out.println("----------------------");
		System.out.println("제목: " + post.title());
		System.out.println("내용: " + post.content());
		if (post.hashtags() != null && !post.hashtags().isEmpty()) {
			System.out.println("해시태그: " + post.hashtags());
		}
		System.out.println("공감: " + post.likeCount() + "\t댓글: " + post.commentCount() + "\t스크랩: " + post.scrapCount());
		System.out.println("======================");
	}
}
