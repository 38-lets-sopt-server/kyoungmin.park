package org.sopt.post.repository;

import java.util.List;
import java.util.Optional;

import org.sopt.post.entity.BoardType;
import org.sopt.post.entity.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long> {

	@Query("""
			SELECT p FROM Post p
			JOIN FETCH p.member
			WHERE p.id = :id
			""")
	Optional<Post> findByIdWithMember(@Param("postId") Long postId);

	@Query("""
			SELECT p FROM Post p
			WHERE (:boardType IS NULL OR p.boardType = :boardType)
			ORDER BY p.createdAt DESC
			""")
	Slice<Post> findAllByBoardType(@Param("boardType") BoardType boardType, Pageable pageable);

	@Query(nativeQuery = true, value = """
			SELECT * FROM post p
			WHERE MATCH(p.title) AGAINST(:title IN NATURAL LANGUAGE MODE)
			LIMIT :limit OFFSET :offset
			""")
	List<Post> findByTitle(@Param("title") String title,
			@Param("limit") int limit,
			@Param("offset") long offset);

	@Modifying
	@Query("UPDATE Post p SET p.likeCount = p.likeCount + 1 WHERE p.id = :postId")
	void increaseLikeCount(@Param("postId") long postId);

	@Modifying
	@Query("UPDATE Post p SET p.likeCount = p.likeCount - 1 WHERE p.id = :postId")
	void decreaseLikeCount(@Param("postId") long postId);
}
