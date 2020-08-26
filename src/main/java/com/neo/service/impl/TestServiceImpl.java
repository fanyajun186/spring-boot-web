package com.neo.service.impl;

import org.apache.camel.util.MessageHelper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neo.config.RabbitConfig;
import com.neo.dao.MsgLogMapper;
import com.neo.domain.mail.Mail;
import com.neo.domain.mail.MsgLog;
import com.neo.domain.common.RespDTO;
import com.neo.domain.common.RespStatusEnum;

@Service
public class TestServiceImpl{

    @Autowired
    private MsgLogMapper msgLogMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public RespDTO send(Mail mail) {
        //String msgId = RandomUtil.UUID32();
        String msgId = "123";
        mail.setMsgId(msgId);

        MsgLog msgLog = new MsgLog(msgId, mail, RabbitConfig.MAIL_EXCHANGE_NAME, RabbitConfig.MAIL_ROUTING_KEY_NAME);
        msgLogMapper.insert(msgLog);// 消息入库

        CorrelationData correlationData = new CorrelationData(msgId);
        rabbitTemplate.convertAndSend(RabbitConfig.MAIL_EXCHANGE_NAME, RabbitConfig.MAIL_ROUTING_KEY_NAME, mail, correlationData);// 发送消息

        return RespDTO.success(RespStatusEnum.SUCCESS.getMsg());
    }

}