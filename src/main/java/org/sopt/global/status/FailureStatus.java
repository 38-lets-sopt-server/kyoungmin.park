package org.sopt.global.status;

import org.springframework.http.HttpStatus;

public enum FailureStatus implements Status {
	TITLE_REQUIRED(HttpStatus.BAD_REQUEST, "제목은(는) 필수입니다."),
	INVALID_TITLE_LENGTH(HttpStatus.BAD_REQUEST, "제목은 50자 이내여야 합니다."),
	CONTENT_REQUIRED(HttpStatus.BAD_REQUEST, "본문은(는) 필수입니다."),
	AUTHOR_REQUIRED(HttpStatus.BAD_REQUEST, "작성자은(는) 필수입니다."),
	INVALID_ANONYMOUS_FLAG(HttpStatus.BAD_REQUEST, "익명 여부는 Y 또는 N만 허용됩니다."),
	POST_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 ID의 게시글을 찾을 수 없습니다."),

	INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류가 발생하였습니다.");

	private final HttpStatus httpStatus;
	private final String message;

	FailureStatus(HttpStatus httpStatus, String message) {
		this.httpStatus = httpStatus;
		this.message = message;
	}

	public boolean isSuccess() {
		return false;
	}

	public HttpStatus getHttpStatus() {
		return this.httpStatus;
	}

	public String getMessage() {
		return this.message;
	}
}
