package org.sopt.global.security.jwt;

import java.time.Instant;
import java.util.Date;

import javax.crypto.SecretKey;

import org.sopt.global.security.util.TokenType;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtProvider {
	private static final String TOKEN_TYPE_CLAIM = "type";

	private final SecretKey secretKey;
	private final JwtProperties jwtProperties;

	public String generateAccessToken(long memberId) {
		return generateToken(
				memberId,
				TokenType.ACCESS.name(),
				jwtProperties.accessTokenExpiresInSeconds()
		);
	}

	public String generateRefreshToken(long memberId) {
		return generateToken(
				memberId,
				TokenType.REFRESH.name(),
				jwtProperties.refreshTokenExpiresInSeconds()
		);
	}

	private String generateToken(
			long memberId,
			String tokenType,
			long expiresInSeconds
	) {
		Instant now = Instant.now();

		return Jwts.builder()
				.issuer(jwtProperties.issuer())
				.subject(String.valueOf(memberId))
				.issuedAt(Date.from(now))
				.expiration(Date.from(now.plusSeconds(expiresInSeconds)))
				.claim(TOKEN_TYPE_CLAIM, tokenType)
				.signWith(secretKey, Jwts.SIG.HS512)
				.compact();
	}
}
