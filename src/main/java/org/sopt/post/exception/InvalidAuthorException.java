package org.sopt.post.exception;

import org.sopt.global.exception.BaseException;
import org.sopt.post.code.FailureCode;

public class InvalidAuthorException extends BaseException {
	public InvalidAuthorException() {
		super(FailureCode.AUTHOR_REQUIRED);
	}
}
