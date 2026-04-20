package org.sopt.global.exception.handler;

import org.sopt.global.exception.BaseException;
import org.sopt.global.response.ApiResponse;
import org.sopt.global.status.code.FailureCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler extends BaseExceptionHandler{
	@ExceptionHandler(BaseException.class)
	protected ResponseEntity<ApiResponse<Void>> handleBaseException(BaseException e) {
		return buildErrorResponse(e.getFailureStatus());
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	protected ResponseEntity<ApiResponse<Void>> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
		Throwable cause = e.getCause();
		if (cause.getCause() instanceof BaseException be) {
			return buildErrorResponse(be.getFailureStatus());
		}
		return buildErrorResponse(FailureCode.INTERNAL_ERROR);
	}

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<ApiResponse<Void>> handleException(Exception e) {
		return buildErrorResponse(FailureCode.INTERNAL_ERROR);
	}
}
