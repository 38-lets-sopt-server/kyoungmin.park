package org.sopt.member.exception;

import org.sopt.global.exception.BaseException;
import org.sopt.member.code.FailureCode;

public class DuplicateEmailException extends BaseException {
	public DuplicateEmailException() {
		super(FailureCode.DUPLICATE_EMAIL);
	}
}
