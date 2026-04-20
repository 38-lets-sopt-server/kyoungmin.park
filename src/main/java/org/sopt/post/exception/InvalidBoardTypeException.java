package org.sopt.post.exception;

import org.sopt.global.exception.BaseException;
import org.sopt.post.code.FailureCode;

public class InvalidBoardTypeException extends BaseException {
	public InvalidBoardTypeException() {
		super(FailureCode.INVALID_BOARD_TYPE);
	}
}
