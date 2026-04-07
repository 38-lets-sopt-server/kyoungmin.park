package org.sopt.post.repository;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sopt.post.domain.Post;

public class PostRepository {
	private final Map<Long, Post> postList = new HashMap<>();
	private Long nextId = 1L;

	public Post save(Post post) {
		postList.put(post.getId(), post);
		return post;
	}

	public List<Post> findAll() {
		return postList.values().stream()
				.sorted(Comparator.comparing(Post::getCreatedAt).reversed())
				.toList();
	}

	public Post findById(Long id) {
		return postList.get(id);
	}

	public boolean deleteById(Long id) {
		return postList.remove(id) != null;
	}

	public Long generateId() {
		return nextId++;
	}
}
