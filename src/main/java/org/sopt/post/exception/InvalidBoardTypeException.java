package org.sopt.post.exception;

import org.sopt.global.exception.BaseException;
import org.sopt.global.status.FailureStatus;

public class InvalidBoardTypeException extends BaseException {
	public InvalidBoardTypeException() {
		super(FailureStatus.INVALID_BOARD_TYPE);
	}
}
