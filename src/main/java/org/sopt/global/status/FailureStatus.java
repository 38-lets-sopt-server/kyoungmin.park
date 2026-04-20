package org.sopt.global.status;

public interface FailureStatus extends Status {
	default boolean isSuccess(){
		return false;
	}
}
