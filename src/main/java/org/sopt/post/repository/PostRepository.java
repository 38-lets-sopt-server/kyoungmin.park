package org.sopt.post.repository;

import java.util.List;
import java.util.Optional;

import org.sopt.post.domain.Post;

public interface PostRepository {
	Post save(Post post);

	List<Post> findAll();

	Optional<Post> findById(long id);

	boolean deleteById(long id);

	long generateId();
}
