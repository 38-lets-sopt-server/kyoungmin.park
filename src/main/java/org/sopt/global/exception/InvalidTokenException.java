package org.sopt.global.exception;

import org.sopt.global.status.code.FailureCode;

public class InvalidTokenException extends BaseException {
	public InvalidTokenException() {
		super(FailureCode.INVALID_TOKEN);
	}
}
