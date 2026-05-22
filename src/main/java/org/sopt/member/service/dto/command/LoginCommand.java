package org.sopt.member.service.dto.command;

public record LoginCommand(
		String email,
		String password
) {
}
