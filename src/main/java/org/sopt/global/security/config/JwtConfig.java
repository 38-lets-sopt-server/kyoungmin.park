package org.sopt.global.security.config;

import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;

import org.sopt.global.security.jwt.JwtProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class JwtConfig {
	private final JwtProperties jwtProperties;

	@Bean
	public SecretKey secretKey() {
		return Keys.hmacShaKeyFor(jwtProperties.secret().getBytes(StandardCharsets.UTF_8));
	}
}
