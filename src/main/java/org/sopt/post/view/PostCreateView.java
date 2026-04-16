package org.sopt.post.view;

import java.util.Scanner;

import org.sopt.post.controller.dto.request.CreatePostRequest;

public class PostCreateView {
	public static CreatePostRequest getCreatePostInput(Scanner sc) {
		printEnterTitleMsg();
		String title = sc.nextLine();

		printEnterContentMsg();
		String content = sc.nextLine();

		printEnterAuthorMsg();
		String author = sc.nextLine();

		printEnterIsAnonymousMsg();
		String isAnonymous = sc.nextLine();

		printEnterHashtagsMsg();
		String hashtags = sc.nextLine();

		return new CreatePostRequest(title, content, author, isAnonymous, hashtags);
	}

	private static void printEnterTitleMsg() {
		System.out.println("\n제목을 입력해주세요.");
		System.out.print("> ");
	}

	private static void printEnterContentMsg() {
		System.out.println("본문을 입력해주세요.");
		System.out.print("> ");
	}

	private static void printEnterAuthorMsg() {
		System.out.println("작성자를 입력해주세요.");
		System.out.print("> ");
	}

	private static void printEnterIsAnonymousMsg() {
		System.out.println("익명 여부를 입력해주세요. (Y/N)");
		System.out.print("> ");
	}

	private static void printEnterHashtagsMsg() {
		System.out.println("해시태그를 입력해주세요. (쉼표로 구분)");
		System.out.print("> ");
	}
}
