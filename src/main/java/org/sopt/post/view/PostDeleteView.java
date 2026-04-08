package org.sopt.post.view;

import java.util.Scanner;

import org.sopt.global.response.CommonResponse;

public class PostDeleteView {
	public static long getPostId(Scanner scanner) {
		printEnterPostIdMsg();
		long id = scanner.nextLong();
		scanner.nextLine();
		return id;
	}

	public static void printPostDeleteResult(CommonResponse<Void> response) {
		if (response.isSuccess()) {
			System.out.println("\n" + response.message());
		} else {
			System.out.println("\n[요청 처리에 실패했습니다] : " + response.message());
		}
	}

	private static void printEnterPostIdMsg() {
		System.out.println("\n삭제할 게시물의 ID를 입력해주세요.");
		System.out.print("> ");
	}
}
