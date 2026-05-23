package org.sopt.global.security.util;

import org.sopt.global.exception.MissingTokenException;
import org.sopt.global.exception.InvalidTokenException;
import org.springframework.http.HttpHeaders;

import jakarta.servlet.http.HttpServletRequest;

public class TokenExtractor {
	private static final String BEARER_PREFIX = "Bearer ";

	public static String extractAccessToken(HttpServletRequest request) {
		String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

		if (authorization == null) {
			throw new MissingTokenException();
		}
		if (!authorization.startsWith(BEARER_PREFIX)) {
			throw new InvalidTokenException();
		}

		return authorization.substring(BEARER_PREFIX.length());
	}
}
