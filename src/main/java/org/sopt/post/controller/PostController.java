package org.sopt.post.controller;

import org.sopt.global.exception.InvalidRequestParamException;
import org.sopt.global.response.ApiResponse;
import org.sopt.global.status.code.FailureCode;
import org.sopt.post.code.SuccessCode;
import org.sopt.post.controller.mapper.PostPresentationMapper;
import org.sopt.post.controller.dto.request.CreatePostRequest;
import org.sopt.post.controller.dto.request.UpdatePostRequest;
import org.sopt.post.controller.dto.response.PostDetailResponse;
import org.sopt.post.controller.dto.response.PostListResponse;
import org.sopt.post.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseEntity<ApiResponse<PostDetailResponse>> createPost(@RequestBody CreatePostRequest request) {
		PostDetailResponse response = PostPresentationMapper.toDetailResponse(
				postService.createPost(PostPresentationMapper.toCommand(request)));

		return ResponseEntity.status(SuccessCode.POST_CREATED.getHttpStatus())
				.body(ApiResponse.success(SuccessCode.POST_CREATED, response));
	}

	// GET /posts
	@GetMapping
	public ResponseEntity<ApiResponse<PostListResponse>> getAllPosts(
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "10") int size
	) {
		validatePage(page);
		validateSize(size);

		PostListResponse response = PostPresentationMapper.toListResponse(postService.getAllPosts(page, size));

		return ResponseEntity.status(SuccessCode.POST_LIST_FOUND.getHttpStatus())
				.body(ApiResponse.success(SuccessCode.POST_LIST_FOUND, response));
	}

	// GET /posts/{id}
	@GetMapping(path = "/{postId}")
	public ResponseEntity<ApiResponse<PostDetailResponse>> getPost(@PathVariable(name = "postId") long id) {
		validatePostId(id);

		PostDetailResponse response = PostPresentationMapper.toDetailResponse(postService.getPost(id));

		return ResponseEntity.status(SuccessCode.POST_FOUND.getHttpStatus())
				.body(ApiResponse.success(SuccessCode.POST_FOUND, response));
	}

	// PUT /posts/{id}
	@PutMapping(path = "/{postId}")
	public ResponseEntity<ApiResponse<PostDetailResponse>> updatePost(
			@PathVariable(name = "postId") long id,
			@RequestBody UpdatePostRequest request
	) {
		validatePostId(id);

		PostDetailResponse response = PostPresentationMapper.toDetailResponse(
				postService.updatePost(request.id(), PostPresentationMapper.toCommand(request)));

		return ResponseEntity.status(SuccessCode.POST_UPDATED.getHttpStatus())
				.body(ApiResponse.success(SuccessCode.POST_UPDATED, response));
	}

	// DELETE /posts/{id}
	@DeleteMapping(path = "/{postId}")
	public ResponseEntity<ApiResponse<Void>> deletePost(@PathVariable(name = "postId") long id) {
		validatePostId(id);

		postService.deletePost(id);

		return ResponseEntity.status(SuccessCode.POST_DELETED.getHttpStatus())
				.body(ApiResponse.success(SuccessCode.POST_DELETED));
	}

	private void validatePostId(long id) {
		if (id < 1) {
			throw new InvalidRequestParamException(FailureCode.INVALID_REQUEST_PARAMETER);
		}
	}

	private void validatePage(int page) {
		if (!isPositive(page)) {
			throw new InvalidRequestParamException(FailureCode.INVALID_REQUEST_PARAMETER);
		}
	}

	private void validateSize(int size) {
		if(!isPositive(size)) {
			throw new InvalidRequestParamException(FailureCode.INVALID_REQUEST_PARAMETER);
		}
	}

	private boolean isPositive(int number) {
		return number > 0;
	}
}
