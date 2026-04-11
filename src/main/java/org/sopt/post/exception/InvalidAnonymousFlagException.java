package org.sopt.post.exception;

import org.sopt.global.exception.BaseException;
import org.sopt.global.status.FailureStatus;

public class InvalidAnonymousFlagException extends BaseException {
	public InvalidAnonymousFlagException() {
		super(FailureStatus.INVALID_ANONYMOUS_FLAG);
	}
}
