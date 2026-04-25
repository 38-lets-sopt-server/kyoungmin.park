package org.sopt.global.status;

import org.springframework.http.HttpStatus;

public interface Status {
	HttpStatus getHttpStatus();
	String getMessage();
}
