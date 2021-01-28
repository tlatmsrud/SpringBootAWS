package com.ssk.book.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {

	/* Target 
	 * 이 어노테이션이 생성될 수 있는 위치를 지정
	 * Parameter로 지정했으니 메소드의 파라미터로 선언된 객체에서만 사용할 수 있음.
	 * 이 외에도 클래스 선언문에 쓸 수 있는  Type 등이 있음.
	 * */
	
	/*Retention
	 * 어느 시점까지 어노테이션의 메모리를 가져갈지.
	 * RunTime으로 선언되어 있으니 런타임 동안 어논테이션의 메모리를 가져감.
	 * */  
}
