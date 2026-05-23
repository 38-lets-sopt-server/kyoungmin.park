package org.sopt.global.exception;

import org.sopt.global.status.code.FailureCode;

public class TokenExpiredException extends BaseException {
	public TokenExpiredException() {
		super(FailureCode.TOKEN_EXPIRED);
	}
}
