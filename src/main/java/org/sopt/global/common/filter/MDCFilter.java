package org.sopt.global.common.filter;

import java.io.IOException;
import java.util.UUID;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class MDCFilter extends OncePerRequestFilter {

	private static final String TRACE_ID_PREFIX = "traceId";

	@Override
	protected void doFilterInternal(
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain
	) throws ServletException, IOException {
		String traceId = UUID.randomUUID().toString().substring(0,8);
		MDC.put(TRACE_ID_PREFIX, traceId);

		try {
			filterChain.doFilter(request, response);
		} finally {
			MDC.clear();
		}
	}
}
