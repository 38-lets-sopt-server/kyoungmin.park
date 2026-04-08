package org.sopt.post.controller;

import org.sopt.global.exception.BaseException;
import org.sopt.global.response.CommonResponse;
import org.sopt.global.status.SuccessStatus;
import org.sopt.post.controller.mapper.PostPresentationMapper;
import org.sopt.post.dto.response.PostDetailResponse;
import org.sopt.post.dto.response.PostListResponse;
import org.sopt.post.model.input.CreatePostInput;
import org.sopt.post.model.output.PostDetailOutput;
import org.sopt.post.model.output.PostListOutput;
import org.sopt.post.service.PostService;

public class PostController {
	private final PostService postService = new PostService();

	// POST /posts
	public CommonResponse<PostDetailOutput> createPost(CreatePostInput input) {
		try {
			input.validate();
			PostDetailResponse response = postService.createPost(PostPresentationMapper.toRequest(input));
			return CommonResponse.success(SuccessStatus.POST_CREATED, PostPresentationMapper.toDetailOutput(response));
		} catch (BaseException e) {
			return CommonResponse.failure(e.getFailureStatus());
		}
	}

	// GET /posts
	public CommonResponse<PostListOutput> getAllPosts() {
		PostListResponse response = postService.getAllPosts();
		return CommonResponse.success(SuccessStatus.POST_LIST_FOUND, PostPresentationMapper.toListOutput(response));
	}

	// GET /posts/{id}
	public CommonResponse<PostDetailOutput> getPost(Long id) {
		try {
			PostDetailResponse response = postService.getPost(id);
			return CommonResponse.success(SuccessStatus.POST_FOUND, PostPresentationMapper.toDetailOutput(response));
		} catch (BaseException e) {
			return CommonResponse.failure(e.getFailureStatus());
		}
	}

	// PUT /posts/{id} 📝 과제
	public void updatePost(Long id, String newTitle, String newContent) {
		// TODO: postService.updatePost() 호출, 예외 발생 시 에러 메시지 출력
	}

	// DELETE /posts/{id} 📝 과제
	public void deletePost(Long id) {
		// TODO: postService.deletePost() 호출, 예외 발생 시 에러 메시지 출력
	}
}