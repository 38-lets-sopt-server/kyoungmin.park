package org.sopt.member.code;

import org.sopt.global.status.SuccessStatus;
import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SuccessCode implements SuccessStatus {
	MEMBER_CREATED(HttpStatus.CREATED, "회원가입이 완료되었습니다!"),
	MEMBER_LOGIN(HttpStatus.OK, "로그인이 완료되었습니다!"),
	MEMBER_TOKEN_REFRESHED(HttpStatus.OK, "토큰이 갱신되었습니다!"),
	MEMBER_LOGOUT(HttpStatus.OK, "로그아웃이 완료되었습니다!");

	private final HttpStatus httpStatus;
	private final String message;
}
