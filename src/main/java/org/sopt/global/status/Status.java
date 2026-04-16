package org.sopt.global.status;

import org.springframework.http.HttpStatus;

public interface Status {
	boolean isSuccess();
	HttpStatus getHttpStatus();
	String getMessage();
}
