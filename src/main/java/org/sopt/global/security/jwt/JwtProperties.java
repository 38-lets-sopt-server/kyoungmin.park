package org.sopt.global.security.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt")
public record JwtProperties(
		String secret,
		String issuer,
		long accessTokenExpiresInSeconds,
		long refreshTokenExpiresInSeconds
) {
}
