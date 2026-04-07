package org.sopt.post.view;

import java.util.Scanner;

import org.sopt.post.model.input.CreatePostInput;

public class PostCreateView {
	public static CreatePostInput getCreatePostInput(Scanner sc) {
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

		return new CreatePostInput(title, content, author, isAnonymous, hashtags);
		}

		private static void printEnterTitleMsg(){
		System.out.print("제목: ");
	}

	private static void printEnterContentMsg(){
		System.out.print("내용: ");
	}

	private static void printEnterAuthorMsg(){
		System.out.print("작성자: ");
	}

	private static void printEnterIsAnonymousMsg(){
		System.out.print("익명 여부(Y/N): ");
	}

	private static void printEnterHashtagsMsg(){
		System.out.print("해시태그(\",\"로 구분): ");
	}
}
