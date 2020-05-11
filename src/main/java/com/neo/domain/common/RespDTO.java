package com.neo.domain.common;


/**
 * 返回值对象
 * @Description:
 * @Author:Sine Chen
 * @Date:Oct 14, 2016 2:09:47 PM
 * @Copyright: All Rights Reserved. Copyright(c) 2016
 */
public class RespDTO<T> extends BaseEntity {
	private int status;
	private String msg;
	private T data;

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

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public static <T> RespDTO<T> success() {
		RespDTO<T> resp = new RespDTO<T>();
		resp.status = RespStatusEnum.SUCCESS.getStatus();
		resp.msg = RespStatusEnum.SUCCESS.getMsg();
		return resp;
	}

	public static <T> RespDTO<T> success(String msg, T data) {
		RespDTO<T> resp = new RespDTO<T>();
		resp.data = data;
		resp.status = RespStatusEnum.SUCCESS.getStatus();
		resp.msg = msg;
		return resp;
	}

	public static <T> RespDTO<T> success(T data) {
		RespDTO<T> resp = new RespDTO<T>();
		resp.data = data;
		resp.status = RespStatusEnum.SUCCESS.getStatus();
		resp.msg = RespStatusEnum.SUCCESS.getMsg();
		return resp;
	}

	public static <T> RespDTO<T> strongMsg(String msg, T data) {
		RespDTO<T> resp = new RespDTO<T>();
		resp.data = data;
		resp.status = RespStatusEnum.STRONG_MSG.getStatus();
		resp.msg = msg;
		return resp;
	}

	public static <T> RespDTO<T> fail() {
		return fail(RespStatusEnum.FAIL.getMsg(), null);
	}

	public static <T> RespDTO<T> fail(String msg) {
		return fail(msg, null);
	}

	public static <T> RespDTO<T> fail(String msg, T data) {
		RespDTO<T> resp = new RespDTO<T>();
		resp.data = data;
		resp.status = RespStatusEnum.FAIL.getStatus();
		resp.msg = msg;
		return resp;
	}

	public static <T> RespDTO<T> forbidden() {
		RespDTO<T> resp = new RespDTO<T>();
		resp.status = RespStatusEnum.FORBIDDEN.getStatus();
		resp.msg = RespStatusEnum.FORBIDDEN.getMsg();
		return resp;
	}

	public static <T> RespDTO<T> pageNotFound() {
		RespDTO<T> resp = new RespDTO<T>();
		resp.status = RespStatusEnum.PAGE_NOT_FOUND.getStatus();
		resp.msg = RespStatusEnum.PAGE_NOT_FOUND.getMsg();
		return resp;
	}

	public static <T> RespDTO<T> badRequest() {
		return badRequest(RespStatusEnum.BAD_REQUEST.getMsg(), null);
	}

	public static <T> RespDTO<T> badRequest(String msg) {
		return badRequest(msg, null);
	}

	public static <T> RespDTO<T> badRequest(String msg, T data) {
		RespDTO<T> resp = new RespDTO<T>();
		resp.data = data;
		resp.status = RespStatusEnum.BAD_REQUEST.getStatus();
		resp.msg = msg;
		return resp;
	}

	public static <T> RespDTO<T> error() {
		return error(RespStatusEnum.SERVER_ERROR.getMsg(), null);
	}

	public static <T> RespDTO<T> error(String msg) {
		return error(msg, null);
	}

	public static <T> RespDTO<T> error(String msg, T data) {
		RespDTO<T> resp = new RespDTO<T>();
		resp.data = data;
		resp.status = RespStatusEnum.SERVER_ERROR.getStatus();
		resp.msg = msg;
		return resp;
	}
}
