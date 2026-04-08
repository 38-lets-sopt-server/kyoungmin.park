package org.sopt.post.exception;

import org.sopt.global.exception.BaseException;
import org.sopt.global.status.FailureStatus;

public class PostNotFoundException extends BaseException {
	public PostNotFoundException() {
		super(FailureStatus.POST_NOT_FOUND);
	}
}
