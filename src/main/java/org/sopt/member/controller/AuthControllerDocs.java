package org.sopt.member.controller;

import org.sopt.global.response.ApiResponse;
import org.sopt.member.controller.dto.request.LoginRequest;
import org.sopt.member.controller.dto.request.SignUpRequest;
import org.sopt.member.controller.dto.response.LoginResponse;
import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "인증 기능", description = "회원가입, 로그인 관련 API")
public interface AuthControllerDocs {
	@Operation(
			summary = "회원가입",
			description = "이메일, 비밀번호로 회원가입합니다. 비밀번호는 영문·숫자·특수문자를 포함한 8~20자여야 합니다."
	)
	ResponseEntity<ApiResponse<Void>> signUp(@Valid SignUpRequest request);

	@Operation(
			summary = "로그인",
			description = "이메일, 비밀번호로 로그인합니다. 액세스 토큰은 Authorization 응답 헤더, 리프레시 토큰은 HttpOnly 쿠키로 전달됩니다."
	)
	ResponseEntity<ApiResponse<LoginResponse>> login(@Valid LoginRequest request);
}
