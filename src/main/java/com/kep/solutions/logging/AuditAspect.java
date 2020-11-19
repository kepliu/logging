package com.kep.solutions.logging;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import brave.Tracer;
import brave.Tracing;

@Aspect
@Component
@ConditionalOnClass(javax.servlet.http.HttpServletRequest.class)
public class AuditAspect {
	static private Logger LOG = LoggerFactory.getLogger("AUDIT_LOGGER"); 
	@Autowired
	Tracer tracer;	
	   @Pointcut("execution(* com.kep.solutions.demo.controller.*.*(..))")
	   private void auditPointCut(){}
	   
	   @Before("auditPointCut()")
	   public void beforeAdvice() {
			final ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
			final HttpServletRequest webRequest = attr.getRequest();
			String traceid = tracer.currentSpan().context().traceIdString();
			MDC.put("uri", webRequest.getRequestURI());
			MDC.put("port", Integer.toString(webRequest.getLocalPort()));
			MDC.put("traceid", traceid);		   
			LOG.info("Before controller ....");
	   }
	   
	   @After("auditPointCut()")
	   public void afterAdvice() {
		   LOG.info("After controller ....");
	   }
}
