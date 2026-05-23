package org.sopt.member.controller;

import org.sopt.global.response.ApiResponse;
import org.sopt.global.response.util.CookieGenerator;
import org.sopt.global.security.jwt.JwtProperties;
import org.sopt.member.code.SuccessCode;
import org.sopt.member.controller.dto.request.LoginRequest;
import org.sopt.member.controller.dto.request.SignUpRequest;
import org.sopt.member.controller.dto.response.LoginResponse;
import org.sopt.member.controller.mapper.AuthPresentationMapper;
import org.sopt.member.service.AuthService;
import org.sopt.member.service.dto.information.LoginInfo;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController implements AuthControllerDocs {
	private static final String REFRESH_TOKEN_COOKIE = "refreshToken";
	private static final String REFRESH_TOKEN_COOKIE_PATH = "/";

	private final AuthService authService;
	private final JwtProperties jwtProperties;

	@PostMapping("/signup")
	public ResponseEntity<ApiResponse<Void>> signUp(
			@RequestBody @Valid SignUpRequest request
	) {
		authService.signUp(AuthPresentationMapper.toCommand(request));

		return ResponseEntity.status(SuccessCode.MEMBER_CREATED.getHttpStatus())
				.body(ApiResponse.success(SuccessCode.MEMBER_CREATED));
	}

	@PostMapping("/login")
	public ResponseEntity<ApiResponse<LoginResponse>> login(
			@RequestBody @Valid LoginRequest request
	) {
		LoginInfo loginInfo = authService.login(AuthPresentationMapper.toCommand(request));

		return ResponseEntity.status(SuccessCode.MEMBER_LOGIN.getHttpStatus())
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + loginInfo.tokens().accessToken())
				.header(HttpHeaders.SET_COOKIE, createRefreshTokenCookie(
						loginInfo.tokens().refreshToken(),
						jwtProperties.refreshTokenExpiresInSeconds()))
				.body(ApiResponse.success(SuccessCode.MEMBER_LOGIN, AuthPresentationMapper.toLoginResponse(loginInfo)));
	}

	private String createRefreshTokenCookie(String refreshToken, long maxAge) {
		return CookieGenerator.buildCookie(
				REFRESH_TOKEN_COOKIE,
				refreshToken,
				REFRESH_TOKEN_COOKIE_PATH,
				maxAge
		).toString();
	}
}
