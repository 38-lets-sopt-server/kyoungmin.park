package org.sopt.post.controller;

import org.sopt.global.response.ApiResponse;
import org.sopt.post.code.SuccessCode;
import org.sopt.post.controller.mapper.PostPresentationMapper;
import org.sopt.post.controller.dto.request.CreatePostRequest;
import org.sopt.post.controller.dto.request.UpdatePostRequest;
import org.sopt.post.controller.dto.response.PostDetailResponse;
import org.sopt.post.controller.dto.response.PostListResponse;
import org.sopt.post.service.PostService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@RestController
@RequestMapping(path = "/api/v1/posts")
public class PostController {
	private final PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}

	// POST /posts
	@PostMapping
	public ResponseEntity<ApiResponse<PostDetailResponse>> createPost(
			@RequestHeader(name = HttpHeaders.AUTHORIZATION) long memberId,
			@RequestBody @Valid CreatePostRequest request
	) {
		PostDetailResponse response = PostPresentationMapper.toDetailResponse(
				postService.createPost(memberId, PostPresentationMapper.toCommand(request)));

		return ResponseEntity.status(SuccessCode.POST_CREATED.getHttpStatus())
				.body(ApiResponse.success(SuccessCode.POST_CREATED, response));
	}

	// GET /posts
	@GetMapping
	public ResponseEntity<ApiResponse<PostListResponse>> getAllPosts(
			@RequestParam(name = "page", defaultValue = "1") @Min(value = 1) int page,
			@RequestParam(name = "size", defaultValue = "10") @Min(value = 1) int size,
			@RequestParam(name = "boardType", required = false) String boardType
	) {
		PostListResponse response = PostPresentationMapper.toListResponse(
				postService.getAllPosts(page, size, boardType));

		return ResponseEntity.status(SuccessCode.POST_LIST_FOUND.getHttpStatus())
				.body(ApiResponse.success(SuccessCode.POST_LIST_FOUND, response));
	}

	@GetMapping(path = "/search")
	public ResponseEntity<ApiResponse<PostListResponse>> getPostsByTitle(
			@RequestParam(name = "page", defaultValue = "1") @Min(value = 1) int page,
			@RequestParam(name = "size", defaultValue = "10") @Min(value = 1) int size,
			@RequestParam(name = "title") @NotBlank String title
	) {
		PostListResponse response = PostPresentationMapper.toListResponse(
				postService.getPostsByTitle(title, page, size));

		return ResponseEntity.status(SuccessCode.POST_LIST_FOUND.getHttpStatus())
				.body(ApiResponse.success(SuccessCode.POST_LIST_FOUND, response));
	}

	// GET /posts/{id}
	@GetMapping(path = "/{postId}")
	public ResponseEntity<ApiResponse<PostDetailResponse>> getPost(
			@PathVariable(name = "postId") long postId
	) {
		PostDetailResponse response = PostPresentationMapper.toDetailResponse(postService.getPost(postId));

		return ResponseEntity.status(SuccessCode.POST_FOUND.getHttpStatus())
				.body(ApiResponse.success(SuccessCode.POST_FOUND, response));
	}

	// PUT /posts/{id}
	@PutMapping(path = "/{postId}")
	public ResponseEntity<ApiResponse<PostDetailResponse>> updatePost(
			@RequestHeader(name = HttpHeaders.AUTHORIZATION) long memberId,
			@PathVariable(name = "postId") long postId,
			@RequestBody @Valid UpdatePostRequest request
	) {
		PostDetailResponse response = PostPresentationMapper.toDetailResponse(
				postService.updatePost(memberId, postId, PostPresentationMapper.toCommand(request)));

		return ResponseEntity.status(SuccessCode.POST_UPDATED.getHttpStatus())
				.body(ApiResponse.success(SuccessCode.POST_UPDATED, response));
	}

	@PatchMapping(path = "/like/{postId}")
	public ResponseEntity<ApiResponse<Void>> likePost(
			@RequestHeader(name = HttpHeaders.AUTHORIZATION) long memberId,
			@PathVariable(name = "postId") long postId
	) {
		postService.updateLike(memberId, postId);

		return ResponseEntity.status(SuccessCode.POST_LIKE_UPDATED.getHttpStatus())
				.body(ApiResponse.success(SuccessCode.POST_LIKE_UPDATED));
	}

	// DELETE /posts/{id}
	@DeleteMapping(path = "/{postId}")
	public ResponseEntity<ApiResponse<Void>> deletePost(
			@RequestHeader(name = HttpHeaders.AUTHORIZATION) long memberId,
			@PathVariable(name = "postId") long postId
	) {
		postService.deletePost(memberId, postId);

		return ResponseEntity.status(SuccessCode.POST_DELETED.getHttpStatus())
				.body(ApiResponse.success(SuccessCode.POST_DELETED));
	}
}
