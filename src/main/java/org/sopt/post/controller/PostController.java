package org.sopt.post.controller;

import org.sopt.global.exception.BaseException;
import org.sopt.global.response.ApiResponse;
import org.sopt.global.status.SuccessStatus;
import org.sopt.post.controller.mapper.PostPresentationMapper;
import org.sopt.post.controller.dto.request.CreatePostRequest;
import org.sopt.post.controller.dto.request.UpdatePostRequest;
import org.sopt.post.controller.dto.response.PostDetailResponse;
import org.sopt.post.controller.dto.response.PostListResponse;
import org.sopt.post.service.PostService;
import org.sopt.post.service.dto.information.PostDetailInfo;
import org.sopt.post.service.dto.information.PostListInfo;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/posts")
public class PostController {
	private final PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}

	// POST /posts
	@PostMapping
	public ApiResponse<PostDetailResponse> createPost(@RequestBody CreatePostRequest request) {
		try {
			request.validate();
			PostDetailInfo response = postService.createPost(PostPresentationMapper.toCommand(request));
			return ApiResponse.success(SuccessStatus.POST_CREATED, PostPresentationMapper.toDetailOutput(response));
		} catch (BaseException e) {
			return ApiResponse.failure(e.getFailureStatus());
		}
	}

	// GET /posts
	@GetMapping
	public ApiResponse<PostListResponse> getAllPosts() {
		PostListInfo response = postService.getAllPosts();
		return ApiResponse.success(SuccessStatus.POST_LIST_FOUND, PostPresentationMapper.toListOutput(response));
	}

	// GET /posts/{id}
	@GetMapping(path = "/{postId}")
	public ApiResponse<PostDetailResponse> getPost(@PathVariable(name = "postId") long id) {
		try {
			PostDetailInfo response = postService.getPost(id);
			return ApiResponse.success(SuccessStatus.POST_FOUND, PostPresentationMapper.toDetailOutput(response));
		} catch (BaseException e) {
			return ApiResponse.failure(e.getFailureStatus());
		}
	}

	// PUT /posts/{id}
	@PutMapping(path = "/{postId}")
	public ApiResponse<PostDetailResponse> updatePost(
			@PathVariable(name = "postId") long id,
			@RequestBody UpdatePostRequest request
	) {
		try {
			request.validate();
			PostDetailInfo response = postService.updatePost(request.id(), PostPresentationMapper.toCommand(request));
			return ApiResponse.success(SuccessStatus.POST_UPDATED, PostPresentationMapper.toDetailOutput(response));
		} catch (BaseException e) {
			return ApiResponse.failure(e.getFailureStatus());
		}
	}

	// DELETE /posts/{id}
	@DeleteMapping(path = "/{postId}")
	public ApiResponse<Void> deletePost(@PathVariable(name = "postId") long id) {
		try {
			postService.deletePost(id);
			return ApiResponse.success(SuccessStatus.POST_DELETED);
		} catch (BaseException e) {
			return ApiResponse.failure(e.getFailureStatus());
		}
	}
}
