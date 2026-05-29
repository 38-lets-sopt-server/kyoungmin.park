package org.sopt.global.common.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.sopt.global.common.aop.annotation.LogExecution;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Slf4j
@Component
public class LoggingAspect {
	@Around("@annotation(logExecution)")
	public Object logging(ProceedingJoinPoint joinPoint, LogExecution logExecution) throws Throwable {
		long startTime = System.currentTimeMillis();

		String className = joinPoint.getSignature()
				.getDeclaringTypeName();

		String methodName = joinPoint.getSignature()
				.getName();

		Object[] args = joinPoint.getArgs();

		try {
			if(logExecution.includeArgs()) {
				log.info("[START] {}.{} \nargs={}", className, methodName, Arrays.toString(args));
			} else {
				log.info("[START] {}.{}", className, methodName);
			}

			Object result = joinPoint.proceed();

			long endTime = System.currentTimeMillis();

			if(logExecution.includeResult()) {
				log.info("[END] {}.{} time={}ms \nresult={}", className, methodName, endTime - startTime, result);
			} else {
				log.info("[END] {}.{} time={}ms", className, methodName, endTime - startTime);
			}

			return result;
		} catch (Exception e) {
			log.error(
					"[ERROR] {}.{}",
					className,
					methodName,
					e
			);

			throw e;
		}
	}
}
