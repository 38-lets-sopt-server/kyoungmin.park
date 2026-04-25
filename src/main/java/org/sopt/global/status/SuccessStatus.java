package org.sopt.global.status;

public interface SuccessStatus extends Status {
	default boolean isSuccess() {
		return true;
	}
}
