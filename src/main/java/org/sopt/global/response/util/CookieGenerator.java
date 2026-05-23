package org.sopt.global.response.util;

import java.time.Duration;

import org.springframework.http.ResponseCookie;

public class CookieGenerator {
	public static ResponseCookie buildCookie(String key, String value, String path, long maxAge) {
		return ResponseCookie.from(key, value)
				.httpOnly(true)
				.secure(true)
				.path(path)
				.maxAge(Duration.ofMillis(maxAge))
				.sameSite("Lax")
				.build();
	}
}
