package org.sopt.post.service;

import org.sopt.post.dto.request.CreatePostRequest;
import org.sopt.post.dto.request.UpdatePostRequest;
import org.sopt.post.dto.response.PostDetailResponse;
import org.sopt.post.dto.response.PostListResponse;
import org.sopt.post.domain.Post;
import org.sopt.post.exception.PostNotFoundException;
import org.sopt.post.repository.PostRepository;
import org.sopt.post.service.mapper.PostMapper;

public class PostService {
	private final PostRepository postRepository = new PostRepository();

	// CREATE
	public PostDetailResponse createPost(CreatePostRequest request) {
		Post newPost = postRepository.save(PostMapper.toDomain(postRepository.generateId(), request));
		return PostMapper.toDetailResponse(newPost);
	}

	// READALL
	public PostListResponse getAllPosts() {
		return PostMapper.toListResponse(postRepository.findAll());
	}

	// READ
	public PostDetailResponse getPost(long id) {
		return PostMapper.toDetailResponse(postRepository.findById(id).orElseThrow(PostNotFoundException::new));
	}

	// UPDATE
	public PostDetailResponse updatePost(long id, UpdatePostRequest request) {
		Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
		post.update(request.title(), request.content(), request.hashtags());
		return PostMapper.toDetailResponse(post);
	}

	// DELETE
	public void deletePost(long id) {
		if (!postRepository.deleteById(id)) {
			throw new PostNotFoundException();
		}
	}
}
