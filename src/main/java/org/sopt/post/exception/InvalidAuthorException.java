package org.sopt.post.exception;

import org.sopt.global.exception.BaseException;
import org.sopt.global.status.FailureStatus;

public class InvalidAuthorException extends BaseException {
	public InvalidAuthorException() {
		super(FailureStatus.AUTHOR_REQUIRED);
	}
}
