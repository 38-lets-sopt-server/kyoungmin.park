package org.sopt.post.exception;

import org.sopt.global.exception.BaseException;
import org.sopt.post.code.FailureCode;

public class InvalidTitleException extends BaseException {
	public InvalidTitleException(FailureCode status) {
		super(status);
	}
}
