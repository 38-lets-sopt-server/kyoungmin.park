package org.sopt.post.service;

import java.util.List;

import org.sopt.post.dto.request.CreatePostRequest;
import org.sopt.post.dto.response.PostDetailResponse;
import org.sopt.post.domain.Post;
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
	// public List<PostResponse> getAllPosts() {
	// 	postRepository.findAll();
	// 	return null;
	// }

	// READ
	// public PostResponse getPost(Long id) {
	// 	postRepository.findById(id);
	// 	return null;
	// }

	// UPDATE
	public void updatePost(Long id, String newTitle, String newContent) {
		// TODO
	}

	// DELETE
	public void deletePost(Long id) {
		postRepository.deleteById(id);
	}
}