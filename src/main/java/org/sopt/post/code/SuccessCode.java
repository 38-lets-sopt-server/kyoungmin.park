package org.sopt.post.code;

import org.sopt.global.status.SuccessStatus;
import org.springframework.http.HttpStatus;

public enum SuccessCode implements SuccessStatus {
	POST_CREATED(HttpStatus.CREATED, "게시글 등록 완료!"),
	POST_FOUND(HttpStatus.OK, "게시글 조회 완료!"),
	POST_LIST_FOUND(HttpStatus.OK, "게시글 목록 조회 완료!"),
	POST_UPDATED(HttpStatus.OK, "게시글 수정 완료!"),
	POST_LIKE_UPDATED(HttpStatus.OK, "게시글 좋아요 상태 변경 완료!"),
	POST_DELETED(HttpStatus.OK, "게시글 삭제 완료!");

	private final HttpStatus httpStatus;
	private final String message;

	SuccessCode(HttpStatus httpStatus, String message) {
		this.httpStatus = httpStatus;
		this.message = message;
	}

	public HttpStatus getHttpStatus() {
		return this.httpStatus;
	}

	public String getMessage() {
		return this.message;
	}
}
