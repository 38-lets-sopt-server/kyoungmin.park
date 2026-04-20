package org.sopt.post.repository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.sopt.post.domain.Post;
import org.springframework.stereotype.Component;

@Component
public class InMemoryPostRepository implements PostRepository {
	private final ConcurrentHashMap<Long, Post> postList = new ConcurrentHashMap<>();
	private final AtomicLong nextId = new AtomicLong(1);

	public Post save(Post post) {
		postList.put(post.getId(), post);
		return post;
	}

	public List<Post> findAll(int page, int size) {
		return postList.values().stream()
				.sorted(Comparator.comparing(Post::getCreatedAt).reversed())
				.skip((long)(page - 1) * size)
				.limit(size)
				.toList();
	}

	public Optional<Post> findById(long id) {
		return Optional.ofNullable(postList.get(id));
	}

	public boolean deleteById(long id) {
		return postList.remove(id) != null;
	}

	public long generateId() {
		return nextId.getAndIncrement();
	}
}
