package org.sopt.global.status;

import org.springframework.http.HttpStatus;

public enum SuccessStatus implements Status {
	POST_CREATED( HttpStatus.CREATED, "게시글 등록 완료!"),
	POST_FOUND(HttpStatus.OK, "게시글 조회 완료!"),
	POST_LIST_FOUND(HttpStatus.OK, "게시글 목록 조회 완료!"),
	POST_UPDATED(HttpStatus.OK, "게시글 수정 완료!"),
	POST_DELETED(HttpStatus.OK, "게시글 삭제 완료!");

	private final HttpStatus httpStatus;
	private final String message;

	SuccessStatus(HttpStatus httpStatus, String message) {
		this.httpStatus = httpStatus;
		this.message = message;
	}

	public boolean isSuccess() {
		return true;
	}

	public HttpStatus getHttpStatus() {
		return this.httpStatus;
	}

	public String getMessage() {
		return this.message;
	}
}
