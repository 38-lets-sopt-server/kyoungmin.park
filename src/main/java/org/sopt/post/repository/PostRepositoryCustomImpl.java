package org.sopt.post.repository;

import java.util.List;

import org.sopt.member.entity.QMember;
import org.sopt.post.entity.Post;
import org.sopt.post.entity.QPost;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

@Repository
public class PostRepositoryCustomImpl implements PostRepositoryCustom {
	private final EntityManager em;
	private final JPAQueryFactory queryFactory;

	private static final QPost post = QPost.post;
	private static final QMember member = QMember.member;

	public PostRepositoryCustomImpl(EntityManager em, JPAQueryFactory queryFactory) {
		this.em = em;
		this.queryFactory = queryFactory;
	}

	@Override
	public Slice<Post> searchByTitle(String title, Pageable pageable) {
		List<Long> ids = em.createNativeQuery("""
                SELECT p.id FROM post p
                WHERE MATCH(p.title) AGAINST(:title IN BOOLEAN MODE)
                LIMIT :limit OFFSET :offset
                """)
				.setParameter("title", title)
				.setParameter("limit", pageable.getPageSize() + 1)
				.setParameter("offset", pageable.getOffset())
				.getResultList();

		boolean hasNext = ids.size() > pageable.getPageSize();
		if (hasNext) {
			ids.remove(ids.size() - 1);
		}

		if (ids.isEmpty()) {
			return new SliceImpl<>(List.of(), pageable, false);
		}

		List<Post> content = queryFactory
				.selectFrom(post)
				.join(post.member, member).fetchJoin()
				.where(post.id.in(ids))
				.fetch();

		return new SliceImpl<>(content, pageable, hasNext);
	}
}