package com.neo.domain.common;

/**
 * 请求返回状态枚举类
 */
public enum RespStatusEnum {
	SUCCESS(0, ""),
	FAIL(1, "操作失败"), 
	STRONG_MSG(2, "操作成功"), //强提示
	BAD_REQUEST(400, "参数异常"), 
	FORBIDDEN(403, "没有访问权限"), 
	PAGE_NOT_FOUND(404, "页面不存在"), 
	SERVER_ERROR(500, "服务器开小差"), 
	NEED_LOGIN(600, "请先登录"),
	KICK_OFF(601, "您的账号已经在其他设备登录");

	private int status;
	private String msg;

	private RespStatusEnum(int status, String msg) {
		this.status = status;
		this.msg = msg;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
