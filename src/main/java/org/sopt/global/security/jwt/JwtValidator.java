package org.sopt.global.security.jwt;

import javax.crypto.SecretKey;

import org.sopt.global.exception.MissingTokenException;
import org.sopt.global.exception.TokenExpiredException;
import org.sopt.global.exception.InvalidTokenException;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtValidator {
	private final SecretKey secretKey;
	private final JwtProperties jwtProperties;

	public Claims validate(String token) {
		try {
			return Jwts.parser()
					.verifyWith(secretKey)
					.requireIssuer(jwtProperties.issuer())
					.build()
					.parseSignedClaims(token)
					.getPayload();
		} catch (ExpiredJwtException e) {
			throw new TokenExpiredException();
		} catch (IllegalArgumentException e) {
			throw new MissingTokenException();
		} catch (JwtException e) {
			throw new InvalidTokenException();
		}
	}
}
