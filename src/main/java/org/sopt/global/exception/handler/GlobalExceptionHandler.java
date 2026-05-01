package org.sopt.global.exception.handler;

import org.sopt.global.exception.BaseException;
import org.sopt.global.response.ApiResponse;
import org.sopt.global.status.code.FailureCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

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

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	protected ResponseEntity<ApiResponse<Void>> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
		return buildErrorResponse(FailureCode.INVALID_REQUEST_PARAMETER);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	protected ResponseEntity<ApiResponse<Void>> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
		return buildErrorResponse(FailureCode.INVALID_REQUEST_PARAMETER);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<ApiResponse<Void>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		return buildErrorResponse(FailureCode.INVALID_REQUEST_PARAMETER);
	}

	@ExceptionHandler(HandlerMethodValidationException.class)
	protected ResponseEntity<ApiResponse<Void>> handleMethodValidationException(HandlerMethodValidationException e) {
		return buildErrorResponse(FailureCode.INVALID_REQUEST_PARAMETER);
	}

	@ExceptionHandler(MissingRequestHeaderException.class)
	protected ResponseEntity<ApiResponse<Void>> handleMissingRequestHeaderException(MissingRequestHeaderException e) {
		return buildErrorResponse(FailureCode.MEMBER_UNAUTHORIZED);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	protected ResponseEntity<ApiResponse<Void>> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
		return buildErrorResponse(FailureCode.INVALID_REQUEST_METHOD);
	}

	@ExceptionHandler(NoResourceFoundException.class)
	protected ResponseEntity<ApiResponse<Void>> handleNoResourceFoundException(NoResourceFoundException e) {
		return buildErrorResponse(FailureCode.RESOURCE_NOTFOUND);
	}

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<ApiResponse<Void>> handleException(Exception e) {
		return buildErrorResponse(FailureCode.INTERNAL_ERROR);
	}
}
