package org.sopt.member.controller.mapper;

import org.sopt.member.controller.dto.request.LoginRequest;
import org.sopt.member.controller.dto.request.SignUpRequest;
import org.sopt.member.controller.dto.response.LoginResponse;
import org.sopt.member.service.dto.command.LoginCommand;
import org.sopt.member.service.dto.command.SignUpCommand;
import org.sopt.member.service.dto.information.LoginInfo;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthPresentationMapper {

	public static SignUpCommand toCommand(SignUpRequest request) {
		return new SignUpCommand(request.nickname(), request.email(), request.password());
	}

	public static LoginCommand toCommand(LoginRequest request) {
		return new LoginCommand(request.email(), request.password());
	}

	public static LoginResponse toLoginResponse(LoginInfo info) {
		return new LoginResponse(info.nickname());
	}
}
