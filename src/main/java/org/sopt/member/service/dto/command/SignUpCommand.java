package org.sopt.member.service.dto.command;

public record SignUpCommand(
		String nickname,
		String email,
		String password
) {
}
