package org.sopt.global.security.jwt;

import org.sopt.global.security.util.TokenType;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtParser {
	private static final String TOKEN_TYPE_CLAIM = "type";

	public long extractMemberId(Claims claims) {
		return Long.parseLong(claims.getSubject());
	}

	public boolean isAccessToken(Claims claims) {
		return TokenType.ACCESS.equals(extractTokenType(claims));
	}

	public boolean isRefreshToken(Claims claims) {
		return TokenType.REFRESH.equals(extractTokenType(claims));
	}

	public long extractExpirationEpochSeconds(Claims claims) {
		return claims.getExpiration()
				.toInstant()
				.getEpochSecond();
	}

	private TokenType extractTokenType(Claims claims) {
		return TokenType.valueOf(claims.get(TOKEN_TYPE_CLAIM, String.class));
	}
}
