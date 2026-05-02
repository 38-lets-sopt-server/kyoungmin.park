package org.sopt.member.code;

import org.sopt.global.status.FailureStatus;
import org.springframework.http.HttpStatus;

public enum FailureCode implements FailureStatus {
	INVALID_MEMBER(HttpStatus.FORBIDDEN, "올바르지 않은 회원입니다!"),
	MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "회원 정보가 존재하지 않습니다!");

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
