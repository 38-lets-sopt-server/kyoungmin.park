package org.sopt.global.status;

public enum SuccessStatus implements Status {
	POST_CREATED( "게시글 등록 완료!"),
	POST_FOUND("게시글 조회 완료!"),
	POST_UPDATED("게시글 수정 완료!"),
	POST_DELETED("게시글 삭제 완료!");


	private final String message;

	SuccessStatus(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return true;
	}

	public String getMessage() {
		return this.message;
	}
}
