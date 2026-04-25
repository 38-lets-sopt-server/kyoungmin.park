package org.sopt.post.repository;

import java.util.List;
import java.util.Optional;

import org.sopt.post.domain.BoardType;
import org.sopt.post.domain.Post;

public interface PostRepository {
	Post save(Post post);

	List<Post> findAll(int page, int size, BoardType boardType);

	Optional<Post> findById(long id);

	boolean deleteById(long id);

	long generateId();
}
