package org.sopt.member.exception;

import org.sopt.global.exception.BaseException;
import org.sopt.member.code.FailureCode;

public class InvalidPasswordException extends BaseException {
	public InvalidPasswordException() {
		super(FailureCode.INVALID_PASSWORD);
	}
}
