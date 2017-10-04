package com.github.lhervier.domino.oauth.client.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.github.lhervier.domino.oauth.client.ex.WrongPathException;

@Component
@Aspect
public class DbCheckAspect {

	/**
	 * The spring environment
	 */
	@Autowired
	private Environment env;
	
	/**
	 * Pointcut to detect controller methods
	 */
	@SuppressWarnings("unused")
	@Pointcut("within(com.github.lhervier.domino.oauth.client.controller.*)")
	private void controller() {
	}
	
	/**
	 * Join point
	 * @param joinPoint the join point
	 * @throws WrongPathException if the context is incorrect
	 */
	@Before("controller()")
	public void checkDb(JoinPoint joinPoint) throws WrongPathException {
		if( this.env.getProperty("oauth2.client.clientId") == null )
			throw new WrongPathException();
	}
}
