package org.sopt;

import java.util.Scanner;

import org.sopt.global.response.CommonResponse;
import org.sopt.post.controller.PostController;
import org.sopt.post.model.input.CreatePostInput;
import org.sopt.post.model.output.PostDetailOutput;
import org.sopt.post.model.output.PostListOutput;
import org.sopt.post.view.MenuView;
import org.sopt.post.view.PostCreateView;
import org.sopt.post.view.PostDetailView;
import org.sopt.post.view.PostListView;

public class Main {
	public static void main(String[] args) {
		// 클라이언트는 Controller만 알면 돼요. Service도 Repository도 몰라도 돼요.
		PostController postController = new PostController();
		Scanner scanner = new Scanner(System.in);
		boolean running = true;

		while (running) {
			MenuView.printMenu();

			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
				case 1:
					CreatePostInput input = PostCreateView.getCreatePostInput(scanner);
					CommonResponse<PostDetailOutput> output = postController.createPost(input);
					PostDetailView.printPostDetail(output);
					break;

				case 2:
					CommonResponse<PostListOutput> listOutput = postController.getAllPosts();
					PostListView.printPostList(listOutput);
					break;

				case 3:
					Long postId = PostDetailView.getPostId(scanner);
					CommonResponse<PostDetailOutput> postResponse = postController.getPost(postId);
					PostDetailView.printPostDetail(postResponse);
					break;

				case 4:
					System.out.print("수정할 게시글 ID: ");
					Long updateId = scanner.nextLong();
					scanner.nextLine();
					System.out.print("새 제목: ");
					String newTitle = scanner.nextLine();
					System.out.print("새 내용: ");
					String newContent = scanner.nextLine();
					postController.updatePost(updateId, newTitle, newContent);
					break;

				case 5:
					System.out.print("삭제할 게시글 ID: ");
					postController.deletePost(scanner.nextLong());
					scanner.nextLine();
					break;

				case 0:
					running = false;
					System.out.println("👋 프로그램 종료");
					break;
				default:
					System.out.println("❗ 잘못된 입력입니다.");
			}
		}
		scanner.close();
	}
}