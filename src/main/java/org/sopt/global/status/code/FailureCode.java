package org.sopt.global.status.code;

import org.sopt.global.status.FailureStatus;
import org.springframework.http.HttpStatus;

public enum FailureCode implements FailureStatus {
	INVALID_REQUEST_PARAMETER(HttpStatus.BAD_REQUEST, "요청값이 올바르지 않습니다."),
	INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류가 발생하였습니다.");

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
