package org.sopt.post.view;

import java.util.Scanner;

import org.sopt.post.controller.dto.request.UpdatePostRequest;

public class PostUpdateView {
	public static UpdatePostRequest getUpdatePostInput(Scanner sc) {
		printEnterPostIdMsg();
		long id = sc.nextLong();
		sc.nextLine();

		printEnterTitleMsg();
		String title = sc.nextLine();

		printEnterContentMsg();
		String content = sc.nextLine();

		printEnterHashtagsMsg();
		String hashtags = sc.nextLine();

		return new UpdatePostRequest(id, title, content, hashtags);
	}

	private static void printEnterPostIdMsg() {
		System.out.println("\n수정할 게시물의 ID를 입력해주세요.");
		System.out.print("> ");
	}

	private static void printEnterTitleMsg() {
		System.out.println("새로운 제목을 입력해주세요.");
		System.out.print("> ");
	}

	private static void printEnterContentMsg() {
		System.out.println("새로운 본문을 입력해주세요.");
		System.out.print("> ");
	}

	private static void printEnterHashtagsMsg() {
		System.out.println("새로운 해시태그를 입력해주세요. (쉼표로 구분)");
		System.out.print("> ");
	}
}
