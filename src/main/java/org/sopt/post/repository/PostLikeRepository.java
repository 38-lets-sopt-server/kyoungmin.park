package org.sopt.post.repository;

import org.sopt.post.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
	@Modifying
	@Query("DELETE FROM PostLike pl WHERE pl.post.id = :postId")
	void deleteByPostId(@Param("postId") long postId);

	void deleteByMemberIdAndPostId(long memberId, long postId);

	boolean existsByMemberIdAndPostId(long memberId, long postId);
}
