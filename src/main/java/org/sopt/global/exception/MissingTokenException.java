package org.sopt.global.exception;

import org.sopt.global.status.code.FailureCode;

public class MissingTokenException extends BaseException {
	public MissingTokenException() {
		super(FailureCode.MISSING_TOKEN);
	}
}
