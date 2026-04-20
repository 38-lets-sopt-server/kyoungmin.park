package org.sopt.global.exception;

import org.sopt.global.status.FailureStatus;

public class InvalidRequestParamException extends BaseException {
	public InvalidRequestParamException(FailureStatus failureStatus) {
		super(failureStatus);
	}
}
