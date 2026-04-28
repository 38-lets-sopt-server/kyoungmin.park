package org.sopt.post.repository;

import org.sopt.post.entity.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface PostRepositoryCustom {
	Slice<Post> searchByTitle(String title, Pageable pageable);
}
