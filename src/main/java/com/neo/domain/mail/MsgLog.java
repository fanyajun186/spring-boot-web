package com.neo.domain.mail;

import java.util.Date;

import lombok.Data;

@Data
public class MsgLog {	
	
	private String msgId;
	private String msg;
	private String exchange;
	private String routingKey;
	private Integer status;
	private Integer tryCount;
	private Date nextTryTime ;
	private Date createTime ;
	private Date updateTime ;
	
	private Mail mail;
	
	public MsgLog(String msgId, Mail mail, String exchange, String routingKey) {
		this.msgId = msgId;
		this.mail = mail;
		this.exchange = exchange;
		this.routingKey = routingKey;
	}

}
