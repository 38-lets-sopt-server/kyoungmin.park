package org.sopt.post.exception;

import org.sopt.global.exception.BaseException;
import org.sopt.global.status.FailureStatus;

public class InvalidTitleException extends BaseException {
	public InvalidTitleException() {
		super(FailureStatus.TITLE_REQUIRED);
	}
}
