package org.sopt.member.service.dto.information;

public record LoginInfo(
		String nickname,
		TokenInfo tokens
) {
}
