package com.neo.domain.common;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 基类，实现序列化接口
 *
 */
public abstract class BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6705418339557986802L;

	@Override
	public String toString() {
		try {
			return this.getClass().getSimpleName() + " = " + JSON.toJSONString(this, SerializerFeature.WriteMapNullValue);
		} catch (Exception e) {
			return super.toString();
		}
	}
}
