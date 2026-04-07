package org.sopt.global.response;

import org.sopt.global.status.FailureStatus;
import org.sopt.global.status.SuccessStatus;

public record CommonResponse<T>(
		boolean isSuccess,
		String message,
		T data
) {
	public static <T> CommonResponse<T> success(SuccessStatus status, T data) {
		return new CommonResponse<>(status.isSuccess(), status.getMessage(), data);
	}

	public static <T> CommonResponse<T> success(SuccessStatus status) {
		return success(status, null);
	}

	public static <T> CommonResponse<T> failure(FailureStatus status) {
		return new CommonResponse<>(status.isSuccess(), status.getMessage(), null);
	}
}
