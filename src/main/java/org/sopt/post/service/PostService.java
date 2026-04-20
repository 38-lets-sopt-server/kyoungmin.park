package org.sopt.post.service;

import org.sopt.post.service.dto.command.CreatePostCommand;
import org.sopt.post.service.dto.command.UpdatePostCommand;
import org.sopt.post.service.dto.information.PostDetailInfo;
import org.sopt.post.service.dto.information.PostListInfo;
import org.sopt.post.domain.Post;
import org.sopt.post.exception.PostNotFoundException;
import org.sopt.post.repository.PostRepository;
import org.sopt.post.service.mapper.PostMapper;
import org.springframework.stereotype.Service;

@Service
public class PostService {
	private final PostRepository postRepository;

	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	// CREATE
	public PostDetailInfo createPost(CreatePostCommand command) {
		Post newPost = postRepository.save(PostMapper.toDomain(postRepository.generateId(), command));
		return PostMapper.toDetailInfo(newPost);
	}

	// READALL
	public PostListInfo getAllPosts(int page, int size) {
		return PostMapper.toListInfo(postRepository.findAll(page, size));
	}

	// READ
	public PostDetailInfo getPost(long id) {
		return PostMapper.toDetailInfo(postRepository.findById(id).orElseThrow(PostNotFoundException::new));
	}

	// UPDATE
	public PostDetailInfo updatePost(long id, UpdatePostCommand command) {
		Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
		post.update(command.title(), command.content(), command.hashtags());
		return PostMapper.toDetailInfo(post);
	}

	// DELETE
	public void deletePost(long id) {
		if (!postRepository.deleteById(id)) {
			throw new PostNotFoundException();
		}
	}
}
