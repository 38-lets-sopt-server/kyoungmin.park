package org.sopt.member.controller.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record SignUpRequest(
		@NotBlank(message = "닉네임은 필수입니다.")
		@Size(max = 20, message = "닉네임은 20자 이내여야 합니다.")
		String nickname,

		@NotBlank(message = "이메일은 필수입니다.")
		@Email(message = "올바른 이메일 형식이 아닙니다.")
		String email,

		@NotBlank(message = "비밀번호는 필수입니다.")
		@Pattern(
				regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%^&*])[a-zA-Z\\d!@#$%^&*]{8,20}$",
				message = "비밀번호는 영문, 숫자, 특수문자(!@#$%^&*)를 포함한 8~20자여야 합니다."
		)
		String password
) {
}
