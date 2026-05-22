package org.sopt.member.exception;

import org.sopt.global.exception.BaseException;
import org.sopt.member.code.FailureCode;

public class InvalidRefreshTokenException extends BaseException {
	public InvalidRefreshTokenException() {
		super(FailureCode.INVALID_REFRESH_TOKEN);
	}
}
