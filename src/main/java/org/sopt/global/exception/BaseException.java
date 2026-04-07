package org.sopt.global.exception;

import org.sopt.global.status.FailureStatus;

public abstract class BaseException extends RuntimeException {
	private final FailureStatus failureStatus;

	public BaseException(FailureStatus failureStatus) {
		super(failureStatus.getMessage());
		this.failureStatus = failureStatus;
	}

	public FailureStatus getFailureStatus() {
		return failureStatus;
	}
}
