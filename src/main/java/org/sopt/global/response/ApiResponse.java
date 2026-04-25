package org.sopt.global.response;

import org.sopt.global.status.FailureStatus;
import org.sopt.global.status.SuccessStatus;

public record ApiResponse<T>(
		boolean isSuccess,
		String message,
		T data
) {
	public static <T> ApiResponse<T> success(SuccessStatus status, T data) {
		return new ApiResponse<>(status.isSuccess(), status.getMessage(), data);
	}

	public static <T> ApiResponse<T> success(SuccessStatus status) {
		return success(status, null);
	}

	public static <T> ApiResponse<T> failure(FailureStatus status) {
		return new ApiResponse<>(status.isSuccess(), status.getMessage(), null);
	}
}
