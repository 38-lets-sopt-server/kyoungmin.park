package org.sopt.global.exception.handler;

import org.sopt.global.response.ApiResponse;
import org.sopt.global.status.FailureStatus;
import org.springframework.http.ResponseEntity;

public abstract class BaseExceptionHandler {
	protected final ResponseEntity<ApiResponse<Void>> buildErrorResponse(FailureStatus failureStatus) {
		return ResponseEntity.status(failureStatus.getHttpStatus())
				.body(ApiResponse.failure(failureStatus));
	}
}
