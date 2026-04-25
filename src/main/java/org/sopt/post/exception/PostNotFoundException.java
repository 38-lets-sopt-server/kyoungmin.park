package org.sopt.post.exception;

import org.sopt.global.exception.BaseException;
import org.sopt.post.code.FailureCode;

public class PostNotFoundException extends BaseException {
	public PostNotFoundException() {
		super(FailureCode.POST_NOT_FOUND);
	}
}
