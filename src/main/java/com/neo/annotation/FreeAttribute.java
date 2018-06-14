package com.neo.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 免检的类属性
 * @author jjs
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FreeAttribute {
	
	String value() default "";
}
