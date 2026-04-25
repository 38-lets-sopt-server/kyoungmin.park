package org.sopt.post.exception;

import org.sopt.global.exception.BaseException;
import org.sopt.post.code.FailureCode;

public class InvalidContentException extends BaseException {
	public InvalidContentException() {
		super(FailureCode.CONTENT_REQUIRED);
	}
}
