package org.sopt.post.exception;

import org.sopt.global.exception.BaseException;
import org.sopt.global.status.FailureStatus;

public class InvalidContentException extends BaseException {
	public InvalidContentException() {
		super(FailureStatus.CONTENT_REQUIRED);
	}
}
