package com.neo.service;

import java.util.Date;
import java.util.List;

import com.neo.domain.mail.MsgLog;

public interface MsgLogService {

	MsgLog selectByMsgId(String msgId);

	void updateStatus(String msgId, int i);

	List<MsgLog> selectTimeoutMsg();

	void updateTryCount(String msgId, Date nextTryTime);

}
