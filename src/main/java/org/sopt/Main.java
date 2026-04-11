package org.sopt;

import java.util.Scanner;

import org.sopt.global.response.CommonResponse;
import org.sopt.post.controller.PostController;
import org.sopt.post.model.input.CreatePostInput;
import org.sopt.post.model.input.UpdatePostInput;
import org.sopt.post.model.output.PostDetailOutput;
import org.sopt.post.model.output.PostListOutput;
import org.sopt.post.view.MenuView;
import org.sopt.post.view.PostCreateView;
import org.sopt.post.view.PostDeleteView;
import org.sopt.post.view.PostDetailView;
import org.sopt.post.view.PostListView;
import org.sopt.post.view.PostUpdateView;

public class Main {
	public static void main(String[] args) {
		// 클라이언트는 Controller만 알면 돼요. Service도 Repository도 몰라도 돼요.
		PostController postController = new PostController();
		Scanner scanner = new Scanner(System.in);
		boolean running = true;

		while (running) {
			MenuView.printMenu();

			int choice = -1;
			try {
				choice = scanner.nextInt();
				scanner.nextLine();
			} catch (Exception e) {
				System.out.println("❗ 숫자를 입력해주세요.");
				scanner.nextLine();
				continue;
			}

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
					long postId = PostDetailView.getPostId(scanner);
					CommonResponse<PostDetailOutput> postOutput = postController.getPost(postId);
					PostDetailView.printPostDetail(postOutput);
					break;

				case 4:
					UpdatePostInput updateInput = PostUpdateView.getUpdatePostInput(scanner);
					CommonResponse<PostDetailOutput> updateOutput = postController.updatePost(updateInput);
					PostDetailView.printPostDetail(updateOutput);
					break;

				case 5:
					long deleteId = PostDeleteView.getPostId(scanner);
					CommonResponse<Void> deleteOutput = postController.deletePost(deleteId);
					PostDeleteView.printPostDeleteResult(deleteOutput);
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
