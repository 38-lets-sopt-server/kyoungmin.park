package org.sopt.member.exception;

import org.sopt.global.exception.BaseException;
import org.sopt.member.code.FailureCode;

public class InvalidMemberException extends BaseException {
	public InvalidMemberException() {
		super(FailureCode.INVALID_MEMBER);
	}
}
