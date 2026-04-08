package org.sopt.post.domain;

import org.sopt.global.status.FailureStatus;
import org.sopt.post.exception.InvalidTitleException;
import java.time.LocalDateTime;
import java.util.List;

public class Post {
	private Long id;          // 게시글 상세 화면 — 특정 게시글 식별용
	private String title;     // 목록, 상세, 글쓰기 화면 — 제목
	private String content;   // 목록(미리보기), 상세(전체) 화면 — 내용
	private String author;    // 목록, 상세 화면 — 글쓴이
	private LocalDateTime createdAt; // 목록, 상세 화면 — 작성 시각
	private boolean isAnonymous;
	private List<String> hashtags;
	private int likeCount;
	private int commentCount;
	private int scrapCount;

	public Post(
			Long id,
			String title,
			String content,
			String author,
			LocalDateTime createdAt,
			boolean isAnonymous,
			List<String> hashtags,
			int likeCount,
			int commentCount,
			int scrapCount
	) {
		validateTitle(title);
		this.id = id;
		this.title = title;
		this.content = content;
		this.author = author;
		this.createdAt = createdAt;
		this.isAnonymous = isAnonymous;
		this.hashtags = hashtags;
		this.likeCount = likeCount;
		this.commentCount = commentCount;
		this.scrapCount = scrapCount;
	}

	public Long getId() { return id; }
	public String getTitle() { return title; }
	public String getContent() { return content; }
	public String getAuthor() { return isAnonymous ? "익명" : author; }
	public LocalDateTime getCreatedAt() { return createdAt; }
	public boolean isAnonymous() { return isAnonymous; }
	public List<String> getHashtags() { return hashtags; }
	public int getLikeCount() { return likeCount; }
	public int getCommentCount() { return commentCount; }
	public int getScrapCount() { return scrapCount; }

	public void update(String title, String content, List<String> hashtags) {
		validateTitle(title);
		this.title = title;
		this.content = content;
		this.hashtags = hashtags;
	}

	private void validateTitle(String title) {
		if (title == null || title.isBlank()) {
			throw new InvalidTitleException(FailureStatus.TITLE_REQUIRED);
		}
		if (title.codePointCount(0, title.length()) > 50) {
			throw new InvalidTitleException(FailureStatus.INVALID_TITLE_LENGTH);
		}
	}

	public String getInfo() {
		return "[" + id + "] " + title + " - " + author + " (" + createdAt + ")\n" + content;
	}
}
