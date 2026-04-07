package org.sopt.global.status;

public enum FailureStatus implements Status {
	TITLE_REQUIRED("제목은(는) 필수입니다."),
	CONTENT_REQUIRED("본문은(는) 필수입니다."),
	AUTHOR_REQUIRED("작성자은(는) 필수입니다."),
	INVALID_ANONYMOUS_FLAG("익명 여부는 Y 또는 N만 허용됩니다.");

	private final String message;

	FailureStatus(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return false;
	}

	public String getMessage() {
		return this.message;
	}
}
