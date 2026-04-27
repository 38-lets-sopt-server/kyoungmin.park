package org.sopt.post.entity;

import org.sopt.global.common.entity.BaseTimeEntity;
import org.sopt.member.entity.Member;
import org.sopt.post.code.FailureCode;
import org.sopt.post.exception.InvalidTitleException;

import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "post",
indexes = {
		@Index(name = "idx_post_member_id", columnList = "member_id")
})
public class Post extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "content", nullable = false)
	private String content;

	@Enumerated(EnumType.STRING)
	@Column(name = "board_type", nullable = false)
	private BoardType boardType;

	@Column(name = "is_anonymous", nullable = false)
	private boolean isAnonymous;

	@Column(name = "like_count", nullable = false)
	private int likeCount;

	@Column(name = "comment_count", nullable = false)
	private int commentCount;

	@Column(name = "scrap_count", nullable = false)
	private int scrapCount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private Member member;

	protected Post() {}

	public Post(
			String title,
			String content,
			BoardType boardType,
			boolean isAnonymous,
			int likeCount,
			int commentCount,
			int scrapCount,
			Member member
	) {
		validateTitle(title);
		this.title = title;
		this.content = content;
		this.boardType = boardType;
		this.isAnonymous = isAnonymous;
		this.likeCount = likeCount;
		this.commentCount = commentCount;
		this.scrapCount = scrapCount;
		this.member = member;
	}

	public Long getId() { return id; }
	public String getTitle() { return title; }
	public String getContent() { return content; }
	public BoardType getBoardType() { return boardType; }
	public boolean isAnonymous() { return isAnonymous; }
	public int getLikeCount() { return likeCount; }
	public int getCommentCount() { return commentCount; }
	public int getScrapCount() { return scrapCount; }
	public Member getMember() { return member; }

	public void update(String title, String content) {
		validateTitle(title);
		this.title = title;
		this.content = content;
	}

	private void validateTitle(String title) {
		if (title == null || title.isBlank()) {
			throw new InvalidTitleException(FailureCode.TITLE_REQUIRED);
		}
		if (title.codePointCount(0, title.length()) > 50) {
			throw new InvalidTitleException(FailureCode.INVALID_TITLE_LENGTH);
		}
	}
}
