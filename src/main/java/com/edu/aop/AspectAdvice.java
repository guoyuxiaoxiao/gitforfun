package com.edu.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class AspectAdvice  {
	
	@Pointcut("execution(* com.edu.base.*.*(..) )")
	public void anymethod(){
		
	}
	/*
	@Before("anymethod()")
	public void doBefore(){
		System.out.println("------------do before---------------");
	}
	@After("anymethod()")
	public void doAfter(){
		System.out.println("------------do after---------------");
	}
	
	 @Around("anymethod()")
	 public Object around(ProceedingJoinPoint pjp) throws Throwable{
		  System.out.println("------------before---------------");
	      Object object = pjp.proceed();
	      System.out.println("------------afer---------------");
	      return object;
	 }*/

}
