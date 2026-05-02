package org.sopt.post.code;

import org.sopt.global.status.FailureStatus;
import org.springframework.http.HttpStatus;

public enum FailureCode implements FailureStatus {
	TITLE_REQUIRED(HttpStatus.BAD_REQUEST, "제목은(는) 필수입니다."),
	INVALID_TITLE_LENGTH(HttpStatus.BAD_REQUEST, "제목은 50자 이내여야 합니다."),
	CONTENT_REQUIRED(HttpStatus.BAD_REQUEST, "본문은(는) 필수입니다."),
	AUTHOR_REQUIRED(HttpStatus.BAD_REQUEST, "작성자은(는) 필수입니다."),
	INVALID_BOARD_TYPE(HttpStatus.BAD_REQUEST, "올바르지 않은 게시판 종류입니다."),
	POST_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 ID의 게시글을 찾을 수 없습니다.");

	private final HttpStatus httpStatus;
	private final String message;

	FailureCode(HttpStatus httpStatus, String message) {
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
