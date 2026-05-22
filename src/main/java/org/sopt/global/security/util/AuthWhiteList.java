package org.sopt.global.security.util;

import java.util.Arrays;

import org.springframework.http.HttpMethod;
import org.springframework.util.AntPathMatcher;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthWhiteList {
	SWAGGER_V3_JSON("/v3/api-docs/**", HttpMethod.GET),
	SWAGGER_RESOURCES("/swagger-resources/**", HttpMethod.GET),
	SWAGGER_UI("/swagger-ui/**", HttpMethod.GET),
	SWAGGER_UI_LEGACY("/swagger-ui.html", HttpMethod.GET),
	SWAGGER_UI_INDEX("/swagger-ui/index.html", HttpMethod.GET),
	SWAGGER_STATIC_RESOURCES("/webjars/**", HttpMethod.GET),

	ROOT_PAGE("/", HttpMethod.GET),
	FAVICON("/favicon.ico", HttpMethod.GET),

	AUTH_SIGNUP("/api/v1/auth/signup", HttpMethod.POST),
	AUTH_LOGIN("/api/v1/auth/login", HttpMethod.POST);

	private static final AntPathMatcher pathMatcher = new AntPathMatcher();
	private final String path;
	private final HttpMethod method;

	public static boolean isPermitted(String requestPath, String httpMethod) {
		return Arrays.stream(values())
				.anyMatch(entry -> pathMatcher.match(entry.getPath(), requestPath)
						&& entry.getMethod().equals(HttpMethod.valueOf(httpMethod)));
	}

	public static String[] getAllowedPaths() {
		return Arrays.stream(values())
				.map(AuthWhiteList::getPath)
				.toArray(String[]::new);
	}
}
