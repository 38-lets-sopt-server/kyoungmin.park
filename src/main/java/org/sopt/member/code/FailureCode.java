package org.sopt.member.code;

import org.sopt.global.status.FailureStatus;
import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FailureCode implements FailureStatus {
	INVALID_MEMBER(HttpStatus.FORBIDDEN, "올바르지 않은 회원입니다!"),
	MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "회원 정보가 존재하지 않습니다!"),
	DUPLICATE_EMAIL(HttpStatus.CONFLICT, "이미 사용 중인 이메일입니다."),
	INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "이메일 또는 비밀번호가 올바르지 않습니다."),
	INVALID_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 리프레시 토큰입니다.");

	private final HttpStatus httpStatus;
	private final String message;
}
