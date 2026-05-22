package org.sopt.global.security.filter;

import java.io.IOException;
import java.util.List;

import org.sopt.global.security.jwt.JwtValidator;
import org.sopt.global.security.util.AuthWhiteList;
import org.sopt.global.security.jwt.JwtParser;
import org.sopt.global.security.util.TokenExtractor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	private final HandlerExceptionResolver resolver;
	private final JwtParser jwtParser;
	private final JwtValidator jwtValidator;

	public JwtAuthenticationFilter(
			@Qualifier("handlerExceptionResolver")
			HandlerExceptionResolver resolver,
			JwtParser jwtParser,
			JwtValidator jwtValidator
	) {
		this.resolver = resolver;
		this.jwtParser = jwtParser;
		this.jwtValidator = jwtValidator;
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return AuthWhiteList.isPermitted(request.getRequestURI(), request.getMethod());
	}

	@Override
	protected void doFilterInternal(
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain
	) throws ServletException, IOException {
		try {
			String accessToken = TokenExtractor.extractAccessToken(request);

			long memberId = jwtParser.extractMemberId(jwtValidator.validate(accessToken));

			UsernamePasswordAuthenticationToken authentication =
					new UsernamePasswordAuthenticationToken(memberId, null, List.of());
			SecurityContextHolder.getContext().setAuthentication(authentication);

			filterChain.doFilter(request, response);
		} catch (Exception e) {
			SecurityContextHolder.clearContext();
			resolver.resolveException(request, response, null, e);
		}
	}
}
