package org.sopt.member.code;

import org.sopt.global.status.SuccessStatus;
import org.springframework.http.HttpStatus;

public enum SuccessCode implements SuccessStatus {
	MEMBER_CREATED(HttpStatus.CREATED, "회원가입이 완료되었습니다!");

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
