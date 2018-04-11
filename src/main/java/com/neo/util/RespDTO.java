package com.neo.util;


public class RespDTO {

	private Integer status;
	private String msg;
	private Object obj;
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	
	public static RespDTO success(Object result) {
		RespDTO r = new RespDTO();
		r.setStatus(0);
		r.setObj(result);
		return r;
	}
	
	public static RespDTO fail(String message) {
		RespDTO r = new RespDTO();
		r.setStatus(1);
		r.setMsg(message);
		return r;
	}	
	
}
