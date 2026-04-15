package org.sopt.post.controller;

import org.sopt.global.exception.BaseException;
import org.sopt.global.response.CommonResponse;
import org.sopt.global.status.SuccessStatus;
import org.sopt.post.controller.mapper.PostPresentationMapper;
import org.sopt.post.service.dto.response.PostDetailResponse;
import org.sopt.post.service.dto.response.PostListResponse;
import org.sopt.post.model.input.CreatePostInput;
import org.sopt.post.model.input.UpdatePostInput;
import org.sopt.post.model.output.PostDetailOutput;
import org.sopt.post.model.output.PostListOutput;
import org.sopt.post.service.PostService;
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
	public CommonResponse<PostDetailOutput> createPost(@RequestBody CreatePostInput input) {
		try {
			input.validate();
			PostDetailResponse response = postService.createPost(PostPresentationMapper.toRequest(input));
			return CommonResponse.success(SuccessStatus.POST_CREATED, PostPresentationMapper.toDetailOutput(response));
		} catch (BaseException e) {
			return CommonResponse.failure(e.getFailureStatus());
		}
	}

	// GET /posts
	@GetMapping
	public CommonResponse<PostListOutput> getAllPosts() {
		PostListResponse response = postService.getAllPosts();
		return CommonResponse.success(SuccessStatus.POST_LIST_FOUND, PostPresentationMapper.toListOutput(response));
	}

	// GET /posts/{id}
	@GetMapping(path = "/{postId}")
	public CommonResponse<PostDetailOutput> getPost(@PathVariable(name = "postId") long id) {
		try {
			PostDetailResponse response = postService.getPost(id);
			return CommonResponse.success(SuccessStatus.POST_FOUND, PostPresentationMapper.toDetailOutput(response));
		} catch (BaseException e) {
			return CommonResponse.failure(e.getFailureStatus());
		}
	}

	// PUT /posts/{id}
	@PutMapping(path = "/{postId}")
	public CommonResponse<PostDetailOutput> updatePost(
			@PathVariable(name = "postId") long id,
			@RequestBody UpdatePostInput input
	) {
		try {
			input.validate();
			PostDetailResponse response = postService.updatePost(input.id(), PostPresentationMapper.toRequest(input));
			return CommonResponse.success(SuccessStatus.POST_UPDATED, PostPresentationMapper.toDetailOutput(response));
		} catch (BaseException e) {
			return CommonResponse.failure(e.getFailureStatus());
		}
	}

	// DELETE /posts/{id}
	@DeleteMapping(path = "/{postId}")
	public CommonResponse<Void> deletePost(@PathVariable(name = "postId") long id) {
		try {
			postService.deletePost(id);
			return CommonResponse.success(SuccessStatus.POST_DELETED);
		} catch (BaseException e) {
			return CommonResponse.failure(e.getFailureStatus());
		}
	}
}
