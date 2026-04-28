package org.sopt.post.repository;

import java.util.List;

import org.sopt.post.entity.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

@Repository
public class PostRepositoryCustomImpl implements PostRepositoryCustom {
	private final PostRepository postRepository;

	public PostRepositoryCustomImpl(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	@Override
	public Slice<Post> searchByTitle(String title, Pageable pageable) {
		List<Post> content = postRepository.findByTitle(
				title,
				pageable.getPageSize() + 1,
				pageable.getOffset()
		);

		boolean hasNext = content.size() > pageable.getPageSize();
		if (hasNext) {
			content.remove(content.size() - 1);
		}

		return new SliceImpl<>(content, pageable, hasNext);
	}
}
