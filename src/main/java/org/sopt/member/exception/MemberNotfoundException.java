package org.sopt.member.exception;

import org.sopt.global.exception.BaseException;
import org.sopt.member.code.FailureCode;

public class MemberNotfoundException extends BaseException {
	public MemberNotfoundException() {
		super(FailureCode.MEMBER_NOT_FOUND);
	}
}
