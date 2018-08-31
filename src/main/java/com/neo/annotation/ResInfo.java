package com.neo.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 资源信息
 * @Description:
 * @Author:Sine Chen
 * @Date:Apr 13, 2015 11:08:29 AM
 * @Copyright: All Rights Reserved. Copyright(c) 2015
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResInfo {
	String name() default "";

	Authc authc() default Authc.AUTHC;

	public enum Authc {
		AUTHC, FREE_PASS
	}
}